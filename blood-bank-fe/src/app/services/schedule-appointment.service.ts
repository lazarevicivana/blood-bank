import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "../model/Appointment";
import {ScheduleAppointmentEx} from "../model/examination/ScheduleAppointmentEx";
import {AppointmentRequest} from "../model/Requests/AppointmentRequest";
import {ScheduleAppointmentRequest} from "../model/Requests/ScheduleAppointmentRequest";
@Injectable({
  providedIn: 'root'
})
export class ScheduleAppointmentService {

  private apiHost = 'http://localhost:8080/api/v1/schedule-appointments'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }
  createAppointment(appointment: ScheduleAppointmentRequest){
    return this.http.post(this.apiHost,appointment,{headers: this.headers})}

  getAppointmentForExamination(appointmentId: string): Observable<ScheduleAppointmentEx> {
    return this.http.get<ScheduleAppointmentEx>(this.apiHost + `/examination/${appointmentId}`, {headers: this.headers});
  }
}
