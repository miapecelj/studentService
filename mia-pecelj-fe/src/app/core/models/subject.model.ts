import { Professor } from "./professor.model";
import { professorSubject } from "./professorSubject.model";
import { Semester } from "./semester.model";

export interface Subject{
  id:number;
  name:string;
  description?:String;
  noOfEspb:number;
  yearOfStudy:number;
  semester:Semester;
  professors:professorSubject[];

}
