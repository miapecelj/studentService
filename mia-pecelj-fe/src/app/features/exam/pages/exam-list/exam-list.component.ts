import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Exam } from 'src/app/core/models/exam.model';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { ConfirmDialogComponent } from 'src/app/shared';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  styleUrls: ['./exam-list.component.css']
})
export class ExamListComponent implements OnInit {

  exams: Exam[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpExamService:HttpExamService,private router: Router,private modalService: NgbModal,private toastService: ToastService) { }

  ngOnInit(): void {
    this.loadExams();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadExams(){

    this.httpExamService.getByPage(this.currentPage-1,this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      response=>{
      this.exams = response.content;
      this.totalItems=response.totalElements;
      this.pageSize=response.size;
      this.currentPage=response.number+1;
      }
    )
  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.loadExams();
  }
  onDeleteClick(exam: Exam) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam <strong>${exam.subject}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting exam';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedExam(exam)
    );
  }

  deleteSelectedExam(exam: Exam) {
    this.httpExamService.deleteExam(exam).subscribe((response) => {
      this.toastService.show(
        'Exam Deleted ',
        { header: 'Deleting exam', classname: 'bg-success text-light' }
      );
      this.loadExams();
    });
    this.exams.splice( this.exams.indexOf(exam));
  }


}
