import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CenterAdministrator} from "../model/CenterAdministrator";

const httpHeaders ={
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class CenterAdministratorService {
  private aplUrl = 'http://localhost:8080/api/v1/center-admin'

  constructor(private http:HttpClient) { }

  getAvailableAdmins(): Observable<CenterAdministrator[]> {
    return this.http.get<CenterAdministrator[]>(this.aplUrl+'/availableAdmins' ,httpHeaders);
  }

  updateAdminCenter(adminId: string, centerId: string): Observable<CenterAdministrator> {
    return this.http.put<CenterAdministrator>(this.aplUrl+'/updateCenter/'+adminId+'/'+centerId ,httpHeaders);
  }

}
