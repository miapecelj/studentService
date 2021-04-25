import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
    return this.httpClient.get<Page<Subject[]>>(`${environment.baseHttpURL}/${this.controlerPrefix}/filter?pageNo=${page}&pageSize=${size}`)
  }
}
