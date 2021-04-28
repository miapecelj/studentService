import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject as SubjectObservable} from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { City, Subject } from 'src/app/core';
import { Professor } from 'src/app/core/models/professor.model';
import { professorSubject } from 'src/app/core/models/professorSubject.model';
import { Title } from 'src/app/core/models/title.model';
import { HttpCityService } from 'src/app/core/service/http-city.service';
import { HttpProfessorService } from 'src/app/core/service/http-professor.service';
import { HttpSubjectService } from 'src/app/core/service/http-subject.service';
import { HttpTitleService } from 'src/app/core/service/http-title.service';
import { ToastService } from 'src/app/core/service/toast.service';


@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements OnInit {
  professorForm:FormGroup;
  edit:false;
  addedProfessor:Professor;
  destroy$: SubjectObservable<boolean> = new SubjectObservable();
  cities:City[];
  titles:Title[];
  selectedSubject:Subject;
  subjects:Subject[];
  chosenSubjects:professorSubject[]=[];

  constructor(private httpCityService:HttpCityService,private HttpTitleService:HttpTitleService,
     private httpProfessorService:HttpProfessorService,
     private fb: FormBuilder,private route:ActivatedRoute,private router:Router, private toastService: ToastService,
     private httpSubjectService:HttpSubjectService) { }

  ngOnInit(): void {
    this.prepareData();
  }
  ngOnDestroy() {
    this.destroy$.next(true);
  }
  prepareData() {
    this.httpSubjectService.getAll().subscribe(response=>this.subjects=response);
    this.httpCityService.getAll().subscribe(response=>this.cities=response);
    this.HttpTitleService.getAll().subscribe(response=>this.titles=response);
    this.edit = this.route.snapshot.data.edit;
    if (this.edit) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.loadProfessor(id);
    } else {
      this.buildForm();
    }
  }
  buildForm(professor?:Professor){
    this.professorForm = this.fb.group(
      {
        phone:[professor? professor.phone:null,[Validators.minLength(9)]],
        title:[professor? professor.title:null,[Validators.required]],
        firstname:[professor? professor.firstname:null ,[Validators.required, Validators.minLength(3)]],
        lastname:[professor? professor.lastname:null, [Validators.minLength(3),Validators.required]],
        email:[professor? professor.email:null,[Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
        address:[professor? professor.address:null,[Validators.minLength(3)]],
        reelectionDate:[professor? professor.reelectionDate:null,[Validators.required]],
        city:[professor? professor.city:null,[]],
        id:[+this.route.snapshot.paramMap.get('id')],
        subjects:[this.chosenSubjects]
      }
    );
  }

  onSubmit() {
    this.saveProfessor()
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe(
      professor => {
        this.toastService.show('Professor saved!', {header:'Saving Professor', classname: 'bg-success text-light'});
        this.router.navigate(['/professor/professor-list']);
        this.addedProfessor=professor;
      },
      error => {
        this.toastService.show('Professor is not saved: ' + (typeof error.error === 'string'? error.error : error.mesage ) , {header:'Saving Professor', classname: 'bg-danger text-light'});
      }


    );
  }
  saveProfessor() {
    if (this.edit) {
      console.log(this.professorForm.value);
      return this.httpProfessorService.editProfessor(this.professorForm.value)
    } else {
      return this.httpProfessorService.postProfessor(this.professorForm.value)
    }
  }

  loadProfessor(id:number){
    this.httpProfessorService.findById(id)
    .pipe(
      takeUntil(this.destroy$)
    )
    .subscribe( professor => {
      console.log(professor);
      this.buildForm(professor);
    });
  }
  hasErrors(componentName: string, errorCode: string) {
    return  (this.professorForm.get(componentName).dirty || this.professorForm.get(componentName).touched) && this.professorForm.get(componentName).hasError(errorCode);
  }

  // addSelectedSubject(){
  //   console.log(this.chosenSubjects)
  //   const idProfessor = +this.route.snapshot.paramMap.get('id');
  //   const professorSubject: professorSubject={subject:this.selectedSubject}
  //   this.chosenSubjects.push(professorSubject);

  // }
  // removeSubjectAtIndex(){

  // }


}
