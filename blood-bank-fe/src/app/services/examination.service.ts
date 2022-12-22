import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Equipment} from "../model/Equipment";
import {Examination} from "../model/examination/Examination";

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {
  private apiHost = 'http://localhost:8080/api/v1/examination'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }
  createExamination(examination:Examination){
    return this.http.post(this.apiHost,examination,{headers: this.headers})
  }
}
