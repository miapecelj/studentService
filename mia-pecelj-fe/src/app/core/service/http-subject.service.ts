import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject } from '../models';

@Injectable({
  providedIn: 'root'
})
export class HttpSubjectService {

  controlerPrefix = 'subject';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Subject[]>(`${environment.baseHttpURL}/${this.controlerPrefix}`)
  }
}
