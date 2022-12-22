import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Center} from "../model/Center";
import {CenterAdministrator} from "../model/CenterAdministrator";
import {UserResponse} from "../model/UserResponse";
import {MedicalStaffResponse} from "../model/MedicalStaffResponse";
import {Moment} from "moment";

@Injectable({
  providedIn: 'root'
})
export class CenterService {
  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }
  getCenter(id: string): Observable<Center> {
    return this.http.get<Center>(this.apiHost + 'api/v1/center/' + id, {headers: this.headers});
  }
  createCenter(center:Center): Observable<Center> {
    return this.http.post<Center>(this.apiHost + 'api/v1/center' , center,{headers: this.headers});
  }
  getAllCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/v1/center/', {headers: this.headers});
  }
  getOtherCenterAdmins(centerId: string,adminId: string): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + `api/v1/center/other-admins/${centerId}/${adminId}`, {headers: this.headers});
  }
  updateCenter(center:Center): Observable<Center>{
    return this.http.put<Center>(this.apiHost + 'api/v1/center/',center,{headers: this.headers})
  }
  getCentersWithAppointment(selectedTime:Moment): Observable<Center[]>{
    return this.http.post<Center[]>(this.apiHost + 'api/v1/center/with-appointment',selectedTime, {headers: this.headers});
  }
  getAllAdminsForCenter(centerId:string): Observable<MedicalStaffResponse[]>{
    return this.http.get<MedicalStaffResponse[]>(this.apiHost + `api/v1/center/admins/${centerId}`,{headers: this.headers});
  }
}
