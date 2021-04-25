import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Semester } from 'src/app/core/models/semester.model';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';

@Component({
  selector: 'app-subject-add',
  templateUrl: './subject-add.component.html',
  styleUrls: ['./subject-add.component.css']
})
export class SubjectAddComponent implements OnInit {

  semester= Semester;
  subjectForm:FormGroup;

  constructor(private httpSubjectService:HttpSubjectService,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.buildForm();
  }
  buildForm(){
    this.subjectForm = this.fb.group(
      {
        name:['',[Validators.required, Validators.minLength(3)]],
        description:['', Validators.maxLength(10)],
        noOfEspb:['',Validators.required],
        yearOfStudy:['',[Validators.required,Validators.min(1),Validators.max(4)]],
        semester:['',Validators.required]
      }
    )
  }
  addSubject(){
    console.log(this.subjectForm.value);
    //this.httpSubjectService.postSubject(this.subjectForm.value);
  }
  getEnumKeys() {
    return Object.keys(Semester).map( key=> this.semester[key] );
  }

}
