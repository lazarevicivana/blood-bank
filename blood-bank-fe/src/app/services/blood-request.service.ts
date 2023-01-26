import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Center} from "../model/Center";
import {BloodBank} from "../model/BloodBank";
import {BloodRequest} from "../model/Responses/BloodRequest";
import {OfferDto} from "../model/OfferDto";

@Injectable({
  providedIn: 'root'
})
export class BloodRequestService {
  apiHost: string = 'http://localhost:8080/api/v1/blood-contracts';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private readonly httpClient:HttpClient) {
  }
  getContracts(): Observable<BloodRequest[]> {
    return this.httpClient.get<BloodRequest[]>(this.apiHost , {headers: this.headers});
  }

  createOffer(offerDto:OfferDto): Observable<OfferDto> {
    console.log(offerDto)
    return this.httpClient.post<OfferDto>(this.apiHost,offerDto,{headers: this.headers});
  }
}
