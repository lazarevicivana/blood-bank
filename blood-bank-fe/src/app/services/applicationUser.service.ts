import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient,HttpHeaders} from '@angular/common/http'
import {ApplicationUser} from "../model/ApplicationUser";
import {UserResponse} from "../model/UserResponse";

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

  getApplicationUserById(): Observable<ApplicationUser>{
    const url = `${this.aplUrl}/34713840-ddf3-49b2-9cae-47334cb6b31b`

    // @ts-ignore
    return this.http.get(url)

  }

  updateApplicationUser(user: ApplicationUser):Observable<ApplicationUser>{
    console.log(user);
    // @ts-ignore
    return this.http.put(this.aplUrl,user,httpOptions);
  }
  getAllUsers():Observable<UserResponse[]> {
  return this.http.get<UserResponse[]>(this.aplUrl , {headers: this.headers});
}
}
