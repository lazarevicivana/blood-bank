import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PatientValidDonor} from "../model/examination/PatientValidDonor";
import {Equipment} from "../model/Equipment";

@Injectable({
  providedIn: 'root'
})
export class CenterEquipmentService {
  private apiHost = 'http://localhost:8080/api/v1/center-equipment'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }
  getCentersEquipment(centerId:string):Observable<Equipment[]>{
    return this.http.get<Equipment[]>(this.apiHost+`/${centerId}`,{headers: this.headers})
  }

}
