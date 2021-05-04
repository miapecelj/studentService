import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';
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
  constructor(private httpStudent:HttpStudentService,private router: Router,private modalService: NgbModal, private toastService:ToastService) { }
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
  onDeleteClick(student: Student) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete student <strong>${student.firstname}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting student';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedStudent(student)
    );
  }


  deleteSelectedStudent(student: Student) {
    this.httpStudent.deleteStudent(student).subscribe((response) => {
      this.loadStudents();
      this.toastService.show(
        'Professor Deleted ',
        { header: 'Deleting student', classname: 'bg-success text-light' }
      );
    },
    err=>{
      this.toastService.show(
        'Student can not be deleted',
        {header: 'Student is not deleted', classname: 'bg-danger text-light'}
      )
    });

  }

}
