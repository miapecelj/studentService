import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { ConfirmDialogComponent } from 'src/app/shared';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  professors: Professor[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 2;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpProfessor:HttpProfessorService,private router: Router,private modalService: NgbModal) { }
  ngOnInit(): void {
    this.loadStudents();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadStudents(){

    this.httpProfessor.getByPage(this.currentPage-1,this.pageSize)
    .pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      response=>{
      this.professors=response.content;
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
  deleteProfessor(professor:Professor){
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete professor <strong>${professor.firstname} ${professor.lastname}</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting professor';
    modalRef.result.then(
      // NAPOMENA: Ovde ce samo ako je zadovoljen prvi uslov izvrsiti ovo drugo.
      (result) => result === 'Ok' && this.deleteSelectedProfessor(professor)
    );
    }
    deleteSelectedProfessor(professor: Professor) {
       this.httpProfessor.deleteProfessor(professor).subscribe();
      this.professors.splice( this.professors.indexOf(professor));
    }

}
