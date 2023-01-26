import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Coordinate} from "../model/coordinate.model";

@Injectable({
  providedIn: 'root'
})
export class BloodTransportService {
  apiHost: string = 'http://localhost:8080/api/v1/blood-transport/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private readonly httpClient:HttpClient) {
  }
  getCoordinates(): Observable<Coordinate> {
    return this.httpClient.get<Coordinate>(this.apiHost , {headers: this.headers});
  }
}
