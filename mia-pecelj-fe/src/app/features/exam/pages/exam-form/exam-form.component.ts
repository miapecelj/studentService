import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'src/app/core';
import { Exam } from 'src/app/core/models/exam.model';
import { ExamPeriod } from 'src/app/core/models/examPeriod.model';
import { Professor } from 'src/app/core/models/professor.model';
import { HttpExamPeriodService } from 'src/app/core/service/http-exam-period.service';
import { HttpExamService } from 'src/app/core/service/http-exam.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-exam-form',
  templateUrl: './exam-form.component.html',
  styleUrls: ['./exam-form.component.css']
})
export class ExamFormComponent implements OnInit {

  examForm:FormGroup;
  addedExam:Exam;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  subjects:Subject[];
  selectedSubject:Subject;
  professors:Professor[];
  examPeriods:ExamPeriod[];

  constructor(private httpExamService:HttpExamService,private fb: FormBuilder,
    private route:ActivatedRoute,private router:Router, private toastService: ToastService, private httpSubjectService:HttpSubjectService,
    private httpExamPeriodService:HttpExamPeriodService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.httpSubjectService.getAll().subscribe(subjects=>this.subjects=subjects);
    this.httpExamPeriodService.getAll().subscribe(examPeriods=>this.examPeriods=examPeriods);
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadExam(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(exam?:Exam){
    this.examForm
   = this.fb.group(
      {
        subject:[exam? exam.subject:null ,[Validators.required]],
        professor:[exam? exam.professor:null, [Validators.required]],
        dateOfExam:[exam? exam.dateOfExam:null,Validators.required],
        examPeriod:[exam? exam.examPeriod:null,Validators.required],
        id:[+this.route.snapshot.paramMap.get('id')]
      }
    );
  }
  onSubmit() {
    this.saveExam()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      exam => {
        this.toastService.show('Exam saved!', {header:'Saving Exam', classname: 'bg-success text-light'});
        this.router.navigate(['/exam/exam-list']);
        this.addedExam=exam;
      },
      error => {
        this.toastService.show('Exam period is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Exam period', classname: 'bg-danger text-light'});
      }

    );
  }
  saveExam() {
    if (this.edit) {
      return this.httpExamService.editExam(this.examForm.value);
    } else {
      console.log(this.examForm.value.active)
      return this.httpExamService.postExam(this.examForm.value);
    }
  }

  loadExam(id:number){
    this.httpExamService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( exam => {
      console.log(exam);
      this.buildForm(exam);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.examForm
    .get(componentName).dirty || this.examForm.get(componentName).touched) && this.examForm.get(componentName).hasError(errorCode);
  }




}
