import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page, Student } from '../models';

@Injectable({
  providedIn: 'root'
})
export class HttpStudentService {


  controlPrefix='student';
  constructor(private httpClient:HttpClient) { }
  getAll() {
    return this.httpClient.get<Student[]>(`${environment.baseHttpURL}/${this.controlPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Student[]>>(`${environment.baseHttpURL}/${this.controlPrefix}/page?page=${page}&size=${size}`);
  }
  deleteStudent(student:Student):Observable<Student>{
    return this.httpClient.delete<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/${student.id}`);
  }
  postStudent(student:Student):Observable<Student>{
    console.log(student);
    return this.httpClient.post<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/`,student);
  }
  findById(id: number): Observable<Student> {
    return this.httpClient.get<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/${id}`);
  }
  editStudent(student:Student):Observable<Student>{
    console.log(student);
    return this.httpClient.put<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/`,student);
  }
  addExam(idStudent: number, idExam: number) {
    return this.httpClient.post<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/addExam?examId=${idExam}&studentId=${idStudent}`,null);
  }
  deleteExam(idStudent: number, idExam: number):Observable<Student> {
    return this.httpClient.delete<Student>(`${environment.baseHttpURL}/${this.controlPrefix}/removeExam?studentId=${idStudent}&examId=${idExam}`);
  }
}
