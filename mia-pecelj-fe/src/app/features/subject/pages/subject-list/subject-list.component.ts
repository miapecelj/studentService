import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ToastService } from 'src/app/core/service/toast.service';
import { SortableHeaderDirective,SortEvent} from 'src/app/shared';



@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 8;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  constructor(private httpSubject:HttpSubjectService,private router: Router,private modalService: NgbModal,private toastService:ToastService) { }

  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  ngOnInit(): void {
    this.loadSubjects("","");
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadSubjects(column:string,order:string){

    this.httpSubject.getByPage(this.currentPage-1,this.pageSize, column, order)
    .pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      response=>{
      this.subjects = response.content;
      this.totalItems=response.totalElements;
      this.pageSize=response.size;
      this.currentPage=response.number+1;
      }
    )
  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.loadSubjects("","");
  }

  deleteSubject(subject:Subject){

  const modalRef = this.modalService.open(ConfirmDialogComponent);
  modalRef.componentInstance.message = `Are you sure you want to delete city <strong>${subject.name}</strong> ?`;
  modalRef.componentInstance.headerText = 'Deleting subject';
  modalRef.result.then(
    (result) => result === 'Ok' && this.deleteSelectedSubject(subject)
  );
  }
  onDeleteClick(subject: Subject) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete subject <strong>${subject.name}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting subject';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedSubject(subject)
    );
  }


  deleteSelectedSubject(subject: Subject) {
    this.httpSubject.deleteSubject(subject).subscribe((response) => {
      this.loadSubjects("","");
      this.toastService.show(
        'Subject Deleted ',
        { header: 'Deleting subject', classname: 'bg-success text-light' }
      );
    },
    err=>{
      this.toastService.show(
        'Subject can not be deleted',
        {header: 'Subject is not deleted', classname: 'bg-danger text-light'}
      )
    }
    );
  }
  onSort(event: SortEvent) {
      this.loadSubjects(event.column,event.direction)
  }


}
