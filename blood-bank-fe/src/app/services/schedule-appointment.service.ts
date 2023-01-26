import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "../model/Appointment";
import {ScheduleAppointmentEx} from "../model/examination/ScheduleAppointmentEx";
import {AppointmentRequest} from "../model/Requests/AppointmentRequest";
import {ScheduleAppointmentRequest} from "../model/Requests/ScheduleAppointmentRequest";
import {ScheduleAppStaff} from "../model/ScheduleAppStaff";
import {ScheduleAppCustomer} from "../model/ScheduleAppCustomer";
import {AppointmentIdResponse} from "../model/Responses/AppointmentIdResponse";
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
  getScheduledAppointmentForStaff(centerId: string): Observable<ScheduleAppStaff[]> {
    return this.http.get<ScheduleAppStaff[]>(this.apiHost + `/center/${centerId}`, {headers: this.headers});
  }
  getScheduledAppointmentForCustomer(customerId: string): Observable<ScheduleAppCustomer[]> {
    return this.http.get<ScheduleAppCustomer[]>(this.apiHost + `/customer/${customerId}`, {headers: this.headers});
  }
 cancelScheduledAppointment(appointmentId: string): Observable<void> {
    return this.http.delete<void>(this.apiHost + `/${appointmentId}`, {headers: this.headers});
  }

  getScheduleAppIdByAppointmentId(appointmentId: string): Observable<AppointmentIdResponse> {
    return this.http.get<AppointmentIdResponse>(this.apiHost + `/appointment/${appointmentId}`, {headers: this.headers});
  }
}
