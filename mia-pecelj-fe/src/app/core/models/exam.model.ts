import { ExamPeriod } from "./examPeriod.model";
import { Professor } from "./professor.model";
import { Subject } from "./subject.model";

export interface Exam{
  id:number;
  professor:Professor;
  subject:Subject;
  examPeriod:ExamPeriod;
  dateOfExam:Date;
}
