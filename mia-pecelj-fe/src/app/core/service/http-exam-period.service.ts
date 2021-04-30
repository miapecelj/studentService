import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../models';
import { ExamPeriod } from '../models/examPeriod.model';

@Injectable({
  providedIn: 'root'
})
export class HttpExamPeriodService {

  controlPrefix='examPeriod';
  constructor(private httpClient:HttpClient) { }
  getAll() {
    return this.httpClient.get<ExamPeriod[]>(`${environment.baseHttpURL}/${this.controlPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<ExamPeriod[]>>(`${environment.baseHttpURL}/${this.controlPrefix}/page?page=${page}&size=${size}`);
  }
  deleteExamPeriod(examPeriod:ExamPeriod):Observable<ExamPeriod>{
    return this.httpClient.delete<ExamPeriod>(`${environment.baseHttpURL}/${this.controlPrefix}/${examPeriod.id}`);
  }
  postExamPeriod(examPeriod:ExamPeriod):Observable<ExamPeriod>{
    return this.httpClient.post<ExamPeriod>(`${environment.baseHttpURL}/${this.controlPrefix}/`,examPeriod);
  }
  findById(id: number): Observable<ExamPeriod> {
    return this.httpClient.get<ExamPeriod>(`${environment.baseHttpURL}/${this.controlPrefix}/${id}`);
  }
  editExamPeriod(examPeriod:ExamPeriod):Observable<ExamPeriod>{
    return this.httpClient.put<ExamPeriod>(`${environment.baseHttpURL}/${this.controlPrefix}/`,examPeriod);
  }

}
