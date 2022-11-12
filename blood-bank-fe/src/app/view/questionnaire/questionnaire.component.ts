import { Component, OnInit } from '@angular/core';
import {MatStepper} from "@angular/material/stepper";
import {FormControl, FormGroup} from "@angular/forms";
import {QuestionnaireRequest} from "../../model/Questionnaire";
import {TokenStorageService} from "../../services/token-storage.service";
import {ApplicationUserService} from "../../services/applicationUser.service";

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
  constructor(private tokenStorage : TokenStorageService) { }

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
    //this.questionnaire.customerId = user.id;
  }
  next(stepper:MatStepper){
    this.stepper = stepper;
    this.stepper.next();
  }
  onAnswer1(answer: boolean){
    this.questionnaire.isAge = answer;
  }
  onAnswer2(answer: boolean){
    this.questionnaire.isWeight = answer;
  }
  onAnswer3(answer: boolean){
    this.questionnaire.isSexual = answer;
  }
  onAnswer4(answer: boolean){
    this.questionnaire.isPregnant = answer;
  }
  onAnswer5(answer: boolean){
    this.questionnaire.onPeriod = answer;
    }
  onAnswer6(answer: boolean){
      this.questionnaire.hadTransfusion = answer;
    }
  onAnswer7(answer: boolean){
      this.questionnaire.hadCancer = answer;
    }
  onAnswer8(answer: boolean){
      this.questionnaire.useMedication = answer;
    }
  onAnswer9(answer: boolean){
      this.questionnaire.isAllergic = answer;
    }
  onAnswer10(answer: boolean){
      this.questionnaire.isSick = answer;
    }

}
