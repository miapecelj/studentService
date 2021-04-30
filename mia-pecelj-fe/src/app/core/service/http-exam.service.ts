import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../models';
import { Exam } from '../models/exam.model';

@Injectable({
  providedIn: 'root'
})
export class HttpExamService {

  controlPrefix='exam';
  constructor(private httpClient:HttpClient) { }
  getAll() {
    return this.httpClient.get<Exam[]>(`${environment.baseHttpURL}/${this.controlPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Exam[]>>(`${environment.baseHttpURL}/${this.controlPrefix}/page?page=${page}&size=${size}`);
  }
  deleteExam(exam:Exam):Observable<Exam>{
    return this.httpClient.delete<Exam>(`${environment.baseHttpURL}/${this.controlPrefix}/${exam.id}`);
  }
  postExam(exam:Exam):Observable<Exam>{
    console.log(exam);
    return this.httpClient.post<Exam>(`${environment.baseHttpURL}/${this.controlPrefix}/`,exam);
  }
  findById(id: number): Observable<Exam> {
    return this.httpClient.get<Exam>(`${environment.baseHttpURL}/${this.controlPrefix}/${id}`);
  }
  editExam(exam:Exam):Observable<Exam>{
    console.log(exam);
    return this.httpClient.put<Exam>(`${environment.baseHttpURL}/${this.controlPrefix}/`,exam);
  }
}
