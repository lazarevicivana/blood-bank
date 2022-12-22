import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ScheduleAppointmentEx} from "../model/examination/ScheduleAppointmentEx";

@Injectable({
  providedIn: 'root'
})
export class ScheduleAppointmentService {
  private apiHost = 'http://localhost:8080/api/v1/schedule-appointments'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http:HttpClient) { }
  getAppointmentForExamination(appointmentId: string): Observable<ScheduleAppointmentEx> {
    return this.http.get<ScheduleAppointmentEx>(this.apiHost + `/examination/${appointmentId}`, {headers: this.headers});
  }
}
