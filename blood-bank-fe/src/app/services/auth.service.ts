import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CustomerRequest} from "../model/CustomerRequest";

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

  login(username: string, password: string): Observable<any>{
    return this.http.post(AUTH_API , {
      username,
      password
    },{headers: this.headers});
  }
  register(customer: CustomerRequest): Observable<any> {

    return this.http.post(AUTH_API ,
      customer
    ,{headers: this.headers});
  }
}
