import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page, Subject } from '../models';
import { Professor } from '../models/professor.model';

@Injectable({
  providedIn: 'root'
})
export class HttpProfessorService {
  controlPrefix='professor';
  constructor(private httpClient:HttpClient) { }
  getAll() {
    return this.httpClient.get<Professor[]>(`${environment.baseHttpURL}/${this.controlPrefix}`)
  }
  getByPage(page:number, size: number) {
    return this.httpClient.get<Page<Professor[]>>(`${environment.baseHttpURL}/${this.controlPrefix}/page?page=${page}&size=${size}`);
  }
  deleteProfessor(professor:Professor):Observable<Professor>{
    return this.httpClient.delete<Professor>(`${environment.baseHttpURL}/${this.controlPrefix}/${professor.id}`);
  }
  postProfessor(professor:Professor):Observable<Professor>{
    console.log(professor);
    return this.httpClient.post<Professor>(`${environment.baseHttpURL}/${this.controlPrefix}/`,professor);
  }
  findById(id: number): Observable<Professor> {
    return this.httpClient.get<Professor>(`${environment.baseHttpURL}/${this.controlPrefix}/${id}`);
  }
  editProfessor(professor:Professor):Observable<Professor>{
    console.log(professor);
    return this.httpClient.put<Professor>(`${environment.baseHttpURL}/${this.controlPrefix}/`,professor);
  }
  addSubject(subject:Subject,id:number):Observable<Professor>{
    console.log(subject,id)
    return this.httpClient.post<Professor>(`${environment.baseHttpURL}/${this.controlPrefix}/addSubject?id=${id}`,subject)
  }
}
