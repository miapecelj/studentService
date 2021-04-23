import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SubjectRoutingModule } from './subject-routing.module';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';


@NgModule({
  declarations: [
    SubjectListComponent
  ],
  imports: [
    CommonModule,
    SubjectRoutingModule,
    SubjectRoutingModule
  ]
})
export class SubjectModule { }
