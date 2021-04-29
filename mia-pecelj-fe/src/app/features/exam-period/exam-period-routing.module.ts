import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamPeriodDetailsComponent } from './pages/exam-period-details/exam-period-details.component';
import { ExamPeriodFormComponent } from './pages/exam-period-form/exam-period-form.component';
import { ExamPeriodListComponent } from './pages/exam-period-list/exam-period-list.component';

const routes: Routes = [
  {path:'examPeriod-list', component:ExamPeriodListComponent},
  {path:'examPeriod-form/:id', component:ExamPeriodFormComponent, data: {edit: true}},
  {path:'examPeriod-details/:id', component:ExamPeriodDetailsComponent},
  {path:'examPeriod-form', component:ExamPeriodFormComponent, data: {edit: false}},
  {path:'', redirectTo:'examPeriod-list', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamPeriodRoutingModule { }
