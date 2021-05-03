import { Exam } from "./exam.model";
import { Student } from "./student.model";

export interface ExamRegistration{
  exam:Exam;
  student:Student;
  registrationDate:Date;
}
