import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Subject } from '../models';
import { Page } from '../models/page.dto';

@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {

  controlerPrefix = 'subject';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Subject[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Subject[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/page?page=${page}&size=${size}`);
  }
  postSubject(subject:Subject):Observable<Subject>{
    return this.httpClient.post<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}/`,subject);
  }
  findById(id: number): Observable<Subject> {
    return this.httpClient.get<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}/${id}`);
  }
  editSubject(subject:Subject):Observable<Subject>{
    return this.httpClient.put<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}/`,subject);
  }
  deleteSubject(subject:Subject):Observable<Subject>{
    return this.httpClient.delete<Subject>(`${environment.baseHttpURL}/${this.controlerPrefix}/${subject.id}`);
  }


}
