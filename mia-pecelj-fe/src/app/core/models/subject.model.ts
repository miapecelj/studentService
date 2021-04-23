export interface Subject{
  id:number;
  name:string;
  description:String;
  noOfEspb:number;
  yearOfStudy:number;
  semester:Semester;

}
export enum Semester{
  WINTER,SUMMER
}
