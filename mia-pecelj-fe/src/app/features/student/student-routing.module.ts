import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamRegistrationFormComponent } from './pages/exam-registration-form/exam-registration-form.component';
import { ExamRegistrationListComponent } from './pages/exam-registration-list/exam-registration-list.component';
import { StudentDetailsComponent } from './pages/student-details/student-details.component';
import { StudentFormComponent } from './pages/student-form/student-form.component';
import { StudentListComponent } from './pages/student-list/student-list.component';

const routes: Routes = [
  {path:'student-list', component:StudentListComponent},
  {path:'student-form/:id', component:StudentFormComponent, data: {edit: true}},
  {path:'student-details/:id', component:StudentDetailsComponent},
  {path:'student-form', component:StudentFormComponent, data: {edit: false}},
  {path:'student-register-exam/:id', component:ExamRegistrationFormComponent},
  {path:'student-register-exam-list/:id', component:ExamRegistrationListComponent},
  {path:'', redirectTo:'student-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
