import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubjectRoutingModule } from './subject-routing.module';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';
import { SharedModule } from 'src/app/shared';
import { SubjectAddComponent } from './pages/subject-add/subject-add.component';


@NgModule({
  declarations: [
    SubjectListComponent,
    SubjectAddComponent
  ],
  imports: [
    CommonModule,
    SubjectRoutingModule,
    SharedModule
  ]
})
export class SubjectModule { }
