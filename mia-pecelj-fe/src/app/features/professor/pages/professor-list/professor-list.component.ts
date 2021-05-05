import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { ToastService } from 'src/app/core/service/toast.service';
import { ConfirmDialogComponent, SortableHeaderDirective, SortEvent } from 'src/app/shared';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements OnInit {

  professors: Professor[];
  currentPage = 1;
  totalItems = 10;
  pageSize = 8;
  destroy$: Subject<boolean> = new Subject();
  constructor(private httpProfessor:HttpProfessorService,private router: Router,private modalService: NgbModal,private toastService:ToastService) { }
  @ViewChildren(SortableHeaderDirective) headers: QueryList<SortableHeaderDirective>;
  ngOnInit(): void {
    this.loadProfessors("","");
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  loadProfessors(column:string, order:string){

    this.httpProfessor.getByPage(this.currentPage-1,this.pageSize, column, order)
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
    this.loadProfessors("","");
  }
  onDeleteClick(professor: Professor) {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.message = `Are you sure you want to delete professor <strong>${professor.firstname, professor.lastname }</strong> ?`;
    modalRef.componentInstance.headerText = 'Deleting professor';
    modalRef.result.then(
      (result) => result === 'Ok' && this.deleteSelectedProfessor(professor)
    );
  }


  deleteSelectedProfessor(professor: Professor) {
    this.httpProfessor.deleteProfessor(professor).subscribe((response) => {
      this.loadProfessors("","")
      this.toastService.show(
        'Professor Deleted ',
        { header: 'Deleting subject', classname: 'bg-success text-light' }
      );
    },
    err=>{
      this.toastService.show(
        'Professor can not be deleted',
        {header: 'Professor is not deleted', classname: 'bg-danger text-light'}
      )
    });

  }
  onSort(event: SortEvent) {
    console.log(event)
    this.loadProfessors(event.column,event.direction)
}

}
