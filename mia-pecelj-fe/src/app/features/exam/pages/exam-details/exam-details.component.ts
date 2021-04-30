import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Exam } from 'src/app/core/models/exam.model';
import { HttpExamService } from 'src/app/core/service/http-exam.service';

@Component({
  selector: 'app-exam-details',
  templateUrl: './exam-details.component.html',
  styleUrls: ['./exam-details.component.css']
})
export class ExamDetailsComponent implements OnInit {

  exam:Exam;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpExamService:HttpExamService,private route: ActivatedRoute,private router: Router) { }
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadExam(id);
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadExam(id:number){
    this.httpExamService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( exam => {
      this.exam = exam;
    });
  }

}
