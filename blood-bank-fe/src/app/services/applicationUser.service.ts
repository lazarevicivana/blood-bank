import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient,HttpHeaders} from '@angular/common/http'
import {ApplicationUser, ApplicationUserImp} from "../model/ApplicationUser";
import {UserResponse} from "../model/Responses/UserResponse";

const httpOptions ={
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class ApplicationUserService {
  private aplUrl = 'http://localhost:8080/api/v1/applicationUser'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http:HttpClient) { }

  getApplicationUserById(idUser:string): Observable<ApplicationUser>{
    const url = `${this.aplUrl}/${idUser}`
    return this.http.get<ApplicationUser>(url);
  }
  getCustomerById(idUser:string): Observable<ApplicationUserImp>{
    const url = `${this.aplUrl}/${idUser}`
    return this.http.get<ApplicationUserImp>(url);
  }

  updateApplicationUser(user: ApplicationUser):Observable<ApplicationUser>{
    console.log(user);
    return this.http.put<ApplicationUser>(this.aplUrl,user,httpOptions);
  }
  getAllUsers():Observable<UserResponse[]> {
  return this.http.get<UserResponse[]>(this.aplUrl , {headers: this.headers});
}
}
