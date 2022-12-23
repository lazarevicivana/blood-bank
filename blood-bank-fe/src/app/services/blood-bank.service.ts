import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Center} from "../model/Center";
import {BloodBank} from "../model/BloodBank";

@Injectable({
  providedIn: 'root'
})
export class BloodBankService {
  apiHost: string = 'http://localhost:8080/api/v1/blood-bank/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private readonly httpClient:HttpClient) {
  }
  getBanksForCenter(id: string): Observable<BloodBank[]> {
    return this.httpClient.get<BloodBank[]>(this.apiHost + id, {headers: this.headers});
  }
}
