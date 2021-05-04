import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentListComponent } from './pages/student-list/student-list.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { SharedModule } from 'src/app/shared';
import { ExamRegistrationFormComponent } from './pages/exam-registration-form/exam-registration-form.component';
import { ExamRegistrationListComponent } from './pages/exam-registration-list/exam-registration-list.component';


@NgModule({
  declarations: [
    StudentListComponent,
    StudentFormComponent,
    StudentDetailsComponent,
    ExamRegistrationFormComponent,
    ExamRegistrationListComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    SharedModule
  ]
})
export class StudentModule { }
