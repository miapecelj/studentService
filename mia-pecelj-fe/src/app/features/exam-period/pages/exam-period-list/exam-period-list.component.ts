import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ExamPeriod } from 'src/app/core/models/examPeriod.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { ConfirmDialogComponent } from 'src/app/shared';

@Component({
  selector: 'app-exam-period-list',
  templateUrl: './exam-period-list.component.html',
  styleUrls: ['./exam-period-list.component.css']
})
export class ExamPeriodListComponent implements OnInit {
  examPeriods: ExamPeriod[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpExamPeriodService:HttpExamPeriodService,private router: Router,private modalService: NgbModal,private toastService: ToastService) { }

  ngOnInit(): void {
    this.loadExamPeriods();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadExamPeriods(){

    this.httpExamPeriodService.getByPage(this.currentPage-1,this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      response=>{
      this.examPeriods = response.content;
      this.totalItems=response.totalElements;
      this.pageSize=response.size;
      this.currentPage=response.number+1;
      }
    )
  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.loadExamPeriods();
  }
  onDeleteClick(examPeriod: ExamPeriod) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam period <strong>${examPeriod.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam period';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExamPeriod(examPeriod)
    );
  }

  deleteSelectedExamPeriod(examPeriod: ExamPeriod) {
    this.httpExamPeriodService.deleteExamPeriod(examPeriod).subscribe((response) => {
      this.loadExamPeriods();
      this.toastService.show(
        'Exam period Deleted ',
        { header: 'Deleting exam period', classname: 'bg-success text-light' }
      );
    },
    err=>{
      this.toastService.show(
        'Exam period can not be deleted',
        {header: 'Exam period is not deleted', classname: 'bg-danger text-light'}
      )
    });
  }


}
