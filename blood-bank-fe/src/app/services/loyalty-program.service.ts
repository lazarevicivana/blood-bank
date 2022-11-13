import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoyaltyProgram} from "../model/LoyaltyProgram";

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  private aplUrl = 'http://localhost:8080/api/v1/loyaltyProgram'
  constructor(private http:HttpClient) { }

  // @ts-ignore
  getLoyaltyProgramByCustomerId(id:string):Observable<LoyaltyProgram>{
    const url = this.aplUrl+`/customers/${id}`
    return this.http.get<LoyaltyProgram>(url);
  }
}
