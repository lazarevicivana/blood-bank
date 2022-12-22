import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../model/Responses/UserResponse";
import {DonorSearchRequest} from "../model/Requests/DonorSerachRequest";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAllDonors(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/v1/customer/getDonors', {headers: this.headers});
  }

  getCenterDonors(centerId: string): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.apiHost + 'api/v1/customer/getCenterDonors/'+centerId, {headers: this.headers});
  }

  searchAllDonors(dto:DonorSearchRequest): Observable<UserResponse[]> {
    return this.http.post<UserResponse[]>(this.apiHost + 'api/v1/customer/searchDonors',dto ,{headers: this.headers});
  }

  searchCenterDonors(dto:DonorSearchRequest): Observable<UserResponse[]> {
    return this.http.post<UserResponse[]>(this.apiHost + 'api/v1/customer/searchCenterDonors/'+dto.centerId,dto ,{headers: this.headers});
  }

}
