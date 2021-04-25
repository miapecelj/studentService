import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectAddComponent } from './features/subject/pages/subject-add/subject-add.component';

const routes: Routes = [
  {path:'subject', loadChildren: () => import('./features/subject/subject.module').then(m => m.SubjectModule)},
  {path:'subject-add', component: SubjectAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
