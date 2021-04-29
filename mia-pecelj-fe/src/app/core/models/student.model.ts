import { City } from "./city.model";

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
}
