import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-subject-details',
  templateUrl: './subject-details.component.html',
  styleUrls: ['./subject-details.component.css']
})
export class SubjectDetailsComponent implements OnInit {

  subject:Subject;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(private httpSubjectService:HttpSubjectService,private route: ActivatedRoute,private router: Router) { }
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadSubject(id);
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadSubject(id:number){
    this.httpSubjectService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( subject => {

      this.subject = subject;
    });
  }


}
