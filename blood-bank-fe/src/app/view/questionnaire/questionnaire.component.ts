import { Component, OnInit } from '@angular/core';
import {MatStepper} from "@angular/material/stepper";
import {FormControl, FormGroup} from "@angular/forms";
import {QuestionnaireRequest} from "../../model/Questionnaire";
import {TokenStorageService} from "../../services/token-storage.service";
import {ApplicationUserService} from "../../services/applicationUser.service";
import {QuestionnaireService} from "../../services/customer-form.service";

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {
  stepper: MatStepper | undefined;
  questionnaire  = new QuestionnaireRequest();
  questions : string[] =[
    '1. Are you 16 – 65 years old?',
    '2. Do you currently weigh less than 50kg (7 stone 12 pounds)?',
    '3. Have you had sexual intercourse in the last six months without protection?',
    '4. Are you pregnant?',
    '5. Are tou currently on your period?',
    '1.Have you had a blood or blood product transfusion since 1st January 1980?',
    '2.Have you ever had a cancer other than basal cell carcinoma or cervical carcinoma insitu (CIN)?',
    '3. Do you take any medication?',
    '4. Do tou have any allergies?',
    '5. Have tou been sick in the last 7 days?'
  ]
  constructor(private tokenStorage : TokenStorageService,private customerClient : ApplicationUserService, private client: QuestionnaireService) { }

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
    this.customerClient.getApplicationUserById(user.id).subscribe({
      next: response => {
        this.questionnaire.applicationUser = response;
        console.log( this.questionnaire.applicationUser);
      }
      }
    )
  }
  next(stepper:MatStepper){
    this.stepper = stepper;
    this.stepper.next();
  }
  onAnswer1(answer: string){
    this.questionnaire.isAge = this.stringToBoolean(answer);
  }
  onAnswer2(answer: string){
    this.questionnaire.isWeight = this.stringToBoolean(answer);
  }
  onAnswer3(answer: string){
    this.questionnaire.isSexual = this.stringToBoolean(answer);
  }
  onAnswer4(answer: string){
    this.questionnaire.isPregnant = this.stringToBoolean(answer);
  }
  onAnswer5(answer: string){
    this.questionnaire.onPeriod =this.stringToBoolean(answer);
    }
  onAnswer6(answer: string){
      this.questionnaire.hadTransfusion = this.stringToBoolean(answer);
    }
  onAnswer7(answer: string){
      this.questionnaire.hadCancer = this.stringToBoolean(answer);
    }
  onAnswer8(answer: string){
      this.questionnaire.useMedication = this.stringToBoolean(answer);
    }
  onAnswer9(answer: string){
      this.questionnaire.isAllergic = this.stringToBoolean(answer);
    }
  onAnswer10(answer: string){
    console.log(typeof(answer));
      this.questionnaire.isSick = this.stringToBoolean(answer);
    }
  onSubmitQuestionnaire(){
    this.questionnaire.submissionDate = new Date();
    this.client.createQuestionnaire(this.questionnaire).subscribe({
      next: response => {
        console.log(response);
      }
    })
  }
  stringToBoolean(answer : string){
    if(answer === 'yes')
      return true;
    return false;
  }

}
