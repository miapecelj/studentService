import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Student } from 'src/app/core';
import { HttpStudentService } from 'src/app/core/service/http-student.service';


@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {
  student:Student;
  destroy$: Subject<boolean> = new Subject();

  constructor(private httpStudentService:HttpStudentService,private route: ActivatedRoute,private router: Router,) { }
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



}
