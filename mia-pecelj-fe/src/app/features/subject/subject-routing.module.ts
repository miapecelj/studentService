import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectAddComponent } from './pages/subject-add/subject-add.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';

const routes: Routes = [
  {path:'', redirectTo:'subject-list', pathMatch:'full'},
  {path:'subject-list', component:SubjectListComponent},
  {path:'subject-add/:id', component:SubjectAddComponent, data: {edit: true}},
  {path:'subject-add', component:SubjectAddComponent, data: {edit: false}},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
