import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {QuestionnaireRequest} from "../model/Questionnaire";
import {PatientValidDonor} from "../model/examination/PatientValidDonor";

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  private apiHost = 'http://localhost:8080/api/v1/customer-form'
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http:HttpClient) { }

  createQuestionnaire(questionnaireRequest: QuestionnaireRequest){
    return this.http.post(this.apiHost,questionnaireRequest,{headers: this.headers})
  }
  checkIfPatientSuitableBloodDonor(patientId:string):Observable<PatientValidDonor[]>{
    return this.http.get<PatientValidDonor[]>(this.apiHost+`/searchCenterDonors/${patientId}`,{headers: this.headers})
  }
}
