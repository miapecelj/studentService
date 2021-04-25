import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  subjects: Subject[];
  // currentPage = 1;
  // totalItems = 10;
  // pageSize = 2;
  // destroy$: SubjectObservable<boolean> = new SubjectObservable();
  constructor(private httpSubject:HttpSubjectService) { }

  ngOnInit(): void {
    this.loadSubjects();
  }
  // ngOnDestroy() {
  //   this.destroy$.next(true);
  // }
  loadSubjects(){
    this.httpSubject.getAll().subscribe(
      response => {
        this.subjects = response;
      }
    )
  }
  //   this.httpSubject.getByPage(this.currentPage-1,this.pageSize)
  //   .pipe(
  //     takeUntil(this.destroy$)
  //   ).subscribe(
  //     response=>{
  //     this.subjects = response.content;
  //     this.totalItems=response.totalElements;
  //     this.pageSize=response.size;
  //     this.currentPage=response.number+1;
  //     }
  //   )
  // }
  // onPageChange(page: number) {
  //   this.currentPage = page;
  //   this.loadSubjects();
  // }

}
