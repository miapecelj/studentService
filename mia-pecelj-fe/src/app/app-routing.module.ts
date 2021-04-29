import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectAddComponent } from './features/subject/pages/subject-add/subject-add.component';
import { HomePageComponent } from './pages/home-page/home-page.component';

const routes: Routes = [
  {path:'', component:HomePageComponent},
  {path:'subject', loadChildren: () => import('./features/subject/subject.module').then(m => m.SubjectModule)},
  {path:'student', loadChildren: () => import('./features/student/student.module').then(m => m.StudentModule)},
  {path:'professor', loadChildren: () => import('./features/professor/professor.module').then(m => m.ProfessorModule)},
  {path:'examPeriod', loadChildren: () => import('./features/exam-period/exam-period.module').then(m => m.ExamPeriodModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
