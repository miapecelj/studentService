import { Professor } from "./professor.model";
import { ProfessorSubjectId } from "./professorSubjectId.model";
import { Subject } from "./subject.model";

export interface professorSubject{
  id?:ProfessorSubjectId;
  professor?:Professor;
  subject: Subject;
  assignDate?: Date;
}
