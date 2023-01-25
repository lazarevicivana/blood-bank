import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CustomerRequest} from "../model/Requests/CustomerRequest";
import {LoginRequest} from "../model/Requests/LoginRequest";

const AUTH_API = 'http://localhost:8080/api/v1/registration';
// const httpOptions = {
//   headers: new HttpHeaders({'Content-type': 'application/json'})
// }
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  login(user: LoginRequest): Observable<any>{
    return this.http.post(AUTH_API +"/login", user,{headers: this.headers});
  }
  register(customer: CustomerRequest): Observable<any> {
    return this.http.post(AUTH_API, customer,{headers: this.headers});
  }
}
