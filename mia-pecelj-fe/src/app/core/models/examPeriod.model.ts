import { Exam } from "./exam.model";

export interface ExamPeriod{
  id:number;
  name:string;
  startDate:Date;
  endDate:Date;
  active:boolean;
  exams:Exam[];
}
