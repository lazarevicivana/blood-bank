import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CenterAdministrator} from "../model/CenterAdministrator";
import {Center} from "../model/Center";

const httpHeaders ={
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class CenterAdministratorService {
  private apiUrl = 'http://localhost:8080/api/v1/center-admin'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }

  getAvailableAdmins(): Observable<CenterAdministrator[]> {
    return this.http.get<CenterAdministrator[]>(this.apiUrl+'/availableAdmins' ,httpHeaders);
  }

  updateAdminCenter(adminId: string, centerId: string): Observable<CenterAdministrator> {
    return this.http.put<CenterAdministrator>(this.apiUrl+'/updateCenter/'+adminId+'/'+centerId ,httpHeaders);
  }

  createAdmin(admin:CenterAdministrator): Observable<CenterAdministrator> {
    return this.http.post<CenterAdministrator>(this.apiUrl,admin ,httpHeaders);
  }
  getCenterForAdmin(id: string): Observable<Center> {
    return this.http.get<Center>(this.apiUrl + '/center-for-admin/' + id, {headers: this.headers});
  }

}
