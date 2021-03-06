import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FooterComponent, HeaderComponent } from './components';
import { ObjectInfoPipe } from './pipes';
import { SortableHeaderDirective } from './directives/sortable-header.directive';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { GlobalToastComponent } from './components/global-toast/global-toast.component';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    ObjectInfoPipe,
    ConfirmDialogComponent,
    GlobalToastComponent,
    SortableHeaderDirective
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
    ObjectInfoPipe,
    ConfirmDialogComponent,
    GlobalToastComponent,
    SortableHeaderDirective
  ]
})
export class SharedModule { }
