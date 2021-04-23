import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  constructor(private httpSubject:HttpSubjectService) { }

  ngOnInit(): void {
    this.loadSubjects();
  }
  loadSubjects(){
    this.httpSubject.getAll().subscribe(
      response => {
        this.subjects = response;
      }
    )
  }

}
