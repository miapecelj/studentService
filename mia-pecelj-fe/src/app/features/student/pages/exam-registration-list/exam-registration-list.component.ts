import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core';
import { Exam } from 'src/app/core/models/exam.model';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { ConfirmDialogComponent } from 'src/app/shared';

@Component({
  selector: 'app-exam-registration-list',
  templateUrl: './exam-registration-list.component.html',
  styleUrls: ['./exam-registration-list.component.css']
})
export class ExamRegistrationListComponent implements OnInit {

  student:Student;
  destroy$: Subject<boolean> = new Subject();
  selectedExam:Exam;
  constructor(private httpStudentService:HttpStudentService,private router: Router,private route: ActivatedRoute,private modalService: NgbModal, private toastService:ToastService) { }
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadStudent(id);
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadStudent(id:number){
    this.httpStudentService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( student => {
      this.student = student;
    });
  }

  onDeleteClick(exam: Exam) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete exam?`;
    modalRef.componentInstance.headerText = 'Deleting exam';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteExam(exam)
    );
  }


  deleteExam(exam:Exam) {
    console.log(exam.id);
    this.httpStudentService.deleteExam(this.student.id,exam.id).subscribe((response) => {
      console.log(response);
      this.loadStudent(this.student.id);
      this.toastService.show(
        'Exam Deleted ',
        { header: 'Deleting exam', classname: 'bg-success text-light' }
      );

    });

  }

}
