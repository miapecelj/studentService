import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent, HeaderComponent } from './components';
import { ObjectInfoPipe } from './pipes';
import { SortableHeaderDirective } from './directives/sortable-header.directive';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    ObjectInfoPipe
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    NgbModule
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    NgbModule,

    HeaderComponent,
    FooterComponent,
    ObjectInfoPipe
  ]
})
export class SharedModule { }
