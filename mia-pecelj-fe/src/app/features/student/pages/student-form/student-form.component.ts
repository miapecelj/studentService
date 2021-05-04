import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { City, Student } from 'src/app/core';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpStudentService } from 'src/app/core/service/http-student.service';
import { ToastService } from 'src/app/core/service/toast.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit {


  studentForm:FormGroup;
  edit:false;
  addedStudent:Student;
  destroy$: Subject<boolean> = new Subject();
  cities:City[];

  constructor(private httpCityService:HttpCityService, private httpStudentService:HttpStudentService,private fb: FormBuilder,private route:ActivatedRoute,private router:Router, private toastService: ToastService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.httpCityService.getAll().subscribe(response=>this.cities=response);
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadStudent(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(student?:Student){
    this.studentForm = this.fb.group(
      {
        indexNumber:[student? student.indexNumber:null,[Validators.required,Validators.minLength(4),Validators.maxLength(4)]],
        indexYear:[student? student.indexYear:null,[Validators.required,Validators.min(2000),Validators.max(2100)]],
        firstname:[student? student.firstname:null ,[Validators.required, Validators.minLength(3)]],
        lastname:[student? student.lastname:null, [Validators.minLength(3),Validators.required]],
        email:[student? student.email:null,[Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
        address:[student? student.address:null,[Validators.minLength(3)]],
        currentYearOfStudy:[student? student.currentYearOfStudy:null,[Validators.required]],
        city:[student? student.city:null,[]],
        id:[+this.route.snapshot.paramMap.get('id')]

      }
    );
  }
  onSubmit() {
    this.saveStudent()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      student => {
        this.toastService.show('Student saved!', {header:'Saving student', classname: 'bg-success text-light'});
        this.router.navigate(['/student/student-list']);
        this.addedStudent=student;
      },
      error => {
        this.toastService.show('Student is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Student', classname: 'bg-danger text-light'});
      }

    );
  }
  saveStudent() {
    if (this.edit) {
      return this.httpStudentService.editStudent(this.studentForm.value)
    } else {
      return this.httpStudentService.postStudent(this.studentForm.value)
    }
  }

  loadStudent(id:number){
    this.httpStudentService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( student => {
      console.log(student);
      this.buildForm(student);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.studentForm.get(componentName).dirty || this.studentForm.get(componentName).touched) && this.studentForm.get(componentName).hasError(errorCode);
  }


}
