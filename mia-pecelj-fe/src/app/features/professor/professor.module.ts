import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessorRoutingModule } from './professor-routing.module';
import { ProfessorListComponent } from './pages/professor-list/professor-list.component';
import { ProfessorFormComponent } from './pages/professor-form/professor-form.component';
import { SharedModule } from 'src/app/shared';
import { ProfessorDetailsComponent } from './pages/professor-details/professor-details.component';


@NgModule({
  declarations: [
    ProfessorListComponent,
    ProfessorFormComponent,
    ProfessorDetailsComponent
  ],
  imports: [
    CommonModule,
    ProfessorRoutingModule,
    SharedModule
  ]
})
export class ProfessorModule { }
