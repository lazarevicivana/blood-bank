import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Center} from "../model/Center";

@Injectable({
  providedIn: 'root'
})
export class CenterAdminService {
  apiHost: string = 'http://localhost:8080/api/v1/center-admin/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }
  getCenterForAdmin(id: string): Observable<Center> {
    return this.http.get<Center>(this.apiHost + 'center-for-admin/' + id, {headers: this.headers});
  }
}
