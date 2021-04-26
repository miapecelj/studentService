import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

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
  constructor(private httpSubject:HttpSubjectService,private router: Router,private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadSubjects();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadSubjects(){
  //   this.httpSubject.getAll().subscribe(
  //     response => {
  //       this.subjects = response;
  //     }
  //   )
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
  openEditPage(subject:Subject){
    this.router.navigate(['/subject-edit/',subject.id]);

  }
  deleteSubject(subject:Subject){
  //   console.log(subject);
  //   this.httpSubject.deleteSubject(subject).subscribe();
  //   this.subjects.splice( this.subjects.indexOf(subject));
  const modalRef = this.modalService.open(ConfirmDialogComponent);
  modalRef.componentInstance.message = `Are you sure you want to delete city <strong>${subject.name}</strong> ?`;
  modalRef.componentInstance.headerText = 'Deleting subject';
  modalRef.result.then(
    // NAPOMENA: Ovde ce samo ako je zadovoljen prvi uslov izvrsiti ovo drugo.
    (result) => result === 'Ok' && this.deleteSelectedSubject(subject)
  );
  }
  deleteSelectedSubject(subject: Subject) {
     this.httpSubject.deleteSubject(subject).subscribe();
    this.subjects.splice( this.subjects.indexOf(subject));
  }
  openAddPage(){
    this.router.navigate(['/subject-add']);
  }
}
