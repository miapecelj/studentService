
import { Professor } from "./professor.model";
import { Subject } from "./subject.model";

export interface professorSubject{
  professor?:Professor;
  subject: Subject;
  assignDate?: Date;
}
