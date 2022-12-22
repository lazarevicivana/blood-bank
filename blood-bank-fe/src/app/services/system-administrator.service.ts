import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SystemAdministrator} from "../model/SystemAdministratorRequest";

const httpHeaders ={
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class SystemAdministratorService {
  private apiUrl = 'http://localhost:8080/api/v1/systemAdmins'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }

  createAdmin(admin:SystemAdministrator): Observable<SystemAdministrator> {
    return this.http.post<SystemAdministrator>(this.apiUrl,admin ,httpHeaders);
  }


}
