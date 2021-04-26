import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'src/app/core';
import { Subject as SubjectObservable} from 'rxjs';
import { Semester } from 'src/app/core/models/semester.model';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-subject-add',
  templateUrl: './subject-add.component.html',
  styleUrls: ['./subject-add.component.css']
})
export class SubjectAddComponent implements OnInit {

  semester= Semester;
  subjectForm:FormGroup;
  addedSubject:Subject;
  edit:false;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();

  constructor(private httpSubjectService:HttpSubjectService,private fb: FormBuilder,private route:ActivatedRoute,private router:Router, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    // Ovde se uzimaju podaci sa rute. Mogu se dodati staticki i dinamicki.
    // pogledajte city-routing.module.ts fajl
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadSubject(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(subject?:Subject){
    this.subjectForm = this.fb.group(
      {
        name:[subject? subject.name:null ,[Validators.required, Validators.minLength(3)]],
        description:[subject? subject.description:null, Validators.maxLength(200)],
        noOfEspb:[subject? subject.noOfEspb:null,Validators.required],
        yearOfStudy:[subject? subject.yearOfStudy:null,[Validators.required,Validators.min(1),Validators.max(4)]],
        semester:[subject? subject.semester:null,Validators.required],
        id:[+this.route.snapshot.paramMap.get('id')]
      }
    );
  }
  onSubmit() {
    this.saveSubject()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      subject => {
        this.toastService.show('Subject saved!', {header:'Saving subject', classname: 'bg-success text-light'});
        this.router.navigate(['/subject/subject-list']);
        this.addedSubject=subject;
      },
      error => {
        this.toastService.show('Subject is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Subject', classname: 'bg-danger text-light'});
      }

    );
  }
  saveSubject() {
    if (this.edit) {
      return this.httpSubjectService.editSubject(this.subjectForm.value)
    } else {
      return this.httpSubjectService.postSubject(this.subjectForm.value)
    }
  }
  getEnumKeys() {
    return Object.keys(Semester).map( key=> this.semester[key] );
  }
  loadSubject(id:number){
    this.httpSubjectService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( subject => {
      console.log(subject);
      this.buildForm(subject);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.subjectForm.get(componentName).dirty || this.subjectForm.get(componentName).touched) && this.subjectForm.get(componentName).hasError(errorCode);
  }

}
