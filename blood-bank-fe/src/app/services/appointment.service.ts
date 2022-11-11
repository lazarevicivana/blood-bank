import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppointmentResponse} from "../model/AppointmentResponse";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiHost = 'http://localhost:8080/api/v1/appointments'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }

  getAppointmentsForCenter(centerId: string): Observable<AppointmentResponse[]> {
    return this.http.get<AppointmentResponse[]>(this.apiHost + `/center/${centerId}`, {headers: this.headers});
  }
}
