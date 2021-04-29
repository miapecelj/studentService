import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ExamPeriod } from 'src/app/core/models/examPeriod.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-period-form',
  templateUrl: './exam-period-form.component.html',
  styleUrls: ['./exam-period-form.component.css']
})
export class ExamPeriodFormComponent implements OnInit {


  examPeriodForm:FormGroup;
  addedExamPeriod:ExamPeriod;
  edit:false;
  destroy$: Subject<boolean> = new Subject();

  constructor(private httpExamPeriod:HttpExamPeriodService,private fb: FormBuilder,
    private route:ActivatedRoute,private router:Router, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExamPeriod(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(examPeriod?:ExamPeriod){
    this.examPeriodForm
   = this.fb.group(
      {
        name:[examPeriod? examPeriod.name:null ,[Validators.required]],
        startDate:[examPeriod? examPeriod.startDate:null, [Validators.required]],
        endDate:[examPeriod? examPeriod.endDate:null,Validators.required],
        active:[examPeriod? examPeriod.active:null],
        id:[+this.route.snapshot.paramMap.get('id')]
      }
    );
  }
  onSubmit() {
    this.saveExamPeriod()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      examPeriod => {
        this.toastService.show('Exam period saved!', {header:'Saving Exam period', classname: 'bg-success text-light'});
        this.router.navigate(['/examPeriod/examPeriod-list']);
        this.addedExamPeriod=examPeriod;
      },
      error => {
        this.toastService.show('Exam period is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Exam period', classname: 'bg-danger text-light'});
      }

    );
  }
  saveExamPeriod() {
    if (this.edit) {
      return this.httpExamPeriod.editExamPeriod(this.examPeriodForm.value);
    } else {
      console.log(this.examPeriodForm.value.active)
      return this.httpExamPeriod.postExamPeriod(this.examPeriodForm.value);
    }
  }

  loadExamPeriod(id:number){
    this.httpExamPeriod.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( examPeriod => {
      console.log(examPeriod);
      this.buildForm(examPeriod);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.examPeriodForm
    .get(componentName).dirty || this.examPeriodForm.get(componentName).touched) && this.examPeriodForm.get(componentName).hasError(errorCode);
  }

}
