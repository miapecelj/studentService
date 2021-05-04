import { City } from "./city.model";
import { ExamRegistration } from "./examRegistration.model";


export interface Student{
  id:number;
  indexNumber:string;
  indexYear:number;
  firstname:string;
  lastname:string;
  email:string;
  address:string;
  city: City;
  currentYearOfStudy:number;
  exams:ExamRegistration[];
}
