import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ConfirmDialogComponent } from 'src/app/shared';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpStudent:HttpStudentService,private router: Router,private modalService: NgbModal) { }
  ngOnInit(): void {
    this.loadStudents();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadStudents(){

    this.httpStudent.getByPage(this.currentPage-1,this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      response=>{
      this.students=response.content;
      this.totalItems=response.totalElements;
      this.pageSize=response.size;
      this.currentPage=response.number+1;
      }
    )
  }
  onPageChange(page: number) {
    this.currentPage = page;
    this.loadStudents();
  }
  deleteStudent(student:Student){
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete student <strong>${student.firstname} ${student.lastname}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting subject';
    modalRef.result.then(
      // NAPOMENA: Ovde ce samo ako je zadovoljen prvi uslov izvrsiti ovo drugo.
      (result) => result === 'Ok' && this.deleteSelectedStudent(student)
    );
    }
    deleteSelectedStudent(student: Student) {
       this.httpStudent.deleteStudent(student).subscribe();
      this.students.splice( this.students.indexOf(student));
    }

}
