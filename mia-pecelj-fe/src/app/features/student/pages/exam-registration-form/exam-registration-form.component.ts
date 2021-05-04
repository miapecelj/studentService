import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Exam } from 'src/app/core/models/exam.model';
import { ExamPeriod } from 'src/app/core/models/examPeriod.model';
import { ExamRegistration } from 'src/app/core/models/examRegistration.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-registration-form',
  templateUrl: './exam-registration-form.component.html',
  styleUrls: ['./exam-registration-form.component.css']
})
export class ExamRegistrationFormComponent implements OnInit {


  examRegistrationForm:FormGroup;
  addedexamRegistration:ExamRegistration;
  destroy$: Subject<boolean> = new Subject();
  examPeriods: ExamPeriod[]=[]
  exams:Exam[]=[];
  id:number;
  selectedExamPeriod:ExamPeriod;

  constructor(private httpStudentService:HttpStudentService, private httpExamPeriodService:HttpExamPeriodService,private fb: FormBuilder,private route:ActivatedRoute,private router:Router, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.httpExamPeriodService.getAll().subscribe(response=>{this.examPeriods=response
      console.log(this.examPeriods);
      console.log(this.examPeriods.find(examPeriod=>examPeriod.active));
      this.selectedExamPeriod = this.examPeriods.find(examPeriod=>examPeriod.active);
    });

    this.id = +this.route.snapshot.paramMap.get('id');
    this.buildForm();

  }
  buildForm(examRegistration?:ExamRegistration){
    this.examRegistrationForm = this.fb.group(
      {
        exam:[null, Validators.required]

      }
    );
  }
  onSubmit() {
    this.registerExam()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      exam => {
        this.toastService.show('Exam registerd!', {header:'Registering exam', classname: 'bg-success text-light'});
      },
      error => {
        this.toastService.show('Exam is not registerd: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Registering exam', classname: 'bg-danger text-light'});
      }

    );
  }
  registerExam() {
      return this.httpStudentService.addExam(this.id,this.examRegistrationForm.value.exam.id);

  }


  hasErrors(componentName: string, errorCode: string) {
    return  (this.examRegistrationForm.get(componentName).dirty || this.examRegistrationForm.get(componentName).touched) && this.examRegistrationForm.get(componentName).hasError(errorCode);
  }



}
