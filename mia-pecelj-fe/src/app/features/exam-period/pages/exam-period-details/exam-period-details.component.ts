import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ExamPeriod } from 'src/app/core/models/examPeriod.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';

@Component({
  selector: 'app-exam-period-details',
  templateUrl: './exam-period-details.component.html',
  styleUrls: ['./exam-period-details.component.css']
})
export class ExamPeriodDetailsComponent implements OnInit {

  examPeriod:ExamPeriod;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpExamPeriod:HttpExamPeriodService,private route: ActivatedRoute,private router: Router,) { }
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadExamPeriod(id);
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadExamPeriod(id:number){
    this.httpExamPeriod.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( examPeriod => {

      this.examPeriod = examPeriod;
    });
  }
}
