import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { catchError, takeUntil } from 'rxjs/operators';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  constructor(private httpSubject:HttpSubjectService,private router: Router,private modalService: NgbModal,private toastService:ToastService) { }

  ngOnInit(): void {
    this.loadSubjects();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadSubjects(){

    this.httpSubject.getByPage(this.currentPage-1,this.pageSize)
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
    this.loadSubjects();
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
      this.loadSubjects();
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


}
