import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'', loadChildren: () => import('./features/subject/subject.module').then(m => m.SubjectModule)},
  {path:'subject', loadChildren: () => import('./features/subject/subject.module').then(m => m.SubjectModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
