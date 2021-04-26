import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectAddComponent } from './pages/subject-add/subject-add.component';
import { SubjectListComponent } from './pages/subject-list/subject-list.component';

const routes: Routes = [
  {path:'subject-list', component:SubjectListComponent},
  {path:'subject-add/:id', component:SubjectAddComponent, data: {edit: true}},
  //{path:'subject-details/:id', component:SujectDetailsComponent},
  {path:'subject-add', component:SubjectAddComponent, data: {edit: false}},
  {path:'', redirectTo:'city-list', pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
