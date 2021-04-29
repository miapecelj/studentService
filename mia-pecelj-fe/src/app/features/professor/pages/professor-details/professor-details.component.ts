import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'src/app/core';
import { Professor } from 'src/app/core/models/professor.model';
import { professorSubject } from 'src/app/core/models/professorSubject.model';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css']
})
export class ProfessorDetailsComponent implements OnInit {

  professor:Professor;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(private httpProfessorService:HttpProfessorService,private httpSubjectService:HttpSubjectService,
    private route: ActivatedRoute,private router: Router,) { }
  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadProfessor(id);
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadProfessor(id:number){
    this.httpProfessorService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( professor => {

      this.professor = professor;
    });
  }


}
