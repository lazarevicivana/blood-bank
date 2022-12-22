import { Component, OnInit } from '@angular/core';
import {MatStepper} from "@angular/material/stepper";
import {QuestionnaireRequest} from "../../model/Questionnaire";
import {TokenStorageService} from "../../services/token-storage.service";
import {ApplicationUserService} from "../../services/applicationUser.service";
import {QuestionnaireService} from "../../services/customer-form.service";
import {ToastrService} from "ngx-toastr";
import { Router} from "@angular/router";
import { ApplicationUserImp} from "../../model/ApplicationUser";
import {User} from "../../model/User";
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {ScheduleAppointmentRequest} from "../../model/Requests/ScheduleAppointmentRequest";

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {
  stepper: MatStepper | undefined;
  applicationUser = new ApplicationUserImp();
  questionnaire  = new QuestionnaireRequest();
  female : boolean = false;
  appointmentId: string | null =""
  questions : string[] =[
    '1. Are you 16 – 65 years old?',
    '2. Do you currently weigh less than 50kg (7 stone 12 pounds)?',
    '3. Have you had sexual intercourse in the last six months without protection?',
    '6. Are you pregnant?',
    '7. Are you currently on your period?',
    '1. Have you had a blood or blood product transfusion since 1st January 1980?',
    '2. Have you ever had a cancer other than basal cell carcinoma or cervical carcinoma insitu (CIN)?',
    '3. Do you take any medication in last 7 days?',
    '4. Do you have any allergies on skin?',
    '5. Have you been sick in the last 7 days?',
    '6. Have you any therapy in the last 7 days?',
    '7. Is your blood pressure in normal range?',
    '4. Have you been to the dentist in the last 7 days?',
    '5. Have you had a piercing or tattoo done in the past 6 months?',
  ]
    enableSubmit =  false;
  private userToken: User;
  constructor(private router: Router,private toast: ToastrService,private tokenStorage : TokenStorageService,
              private customerClient : ApplicationUserService, private client: QuestionnaireService,
              private readonly router1:Router, private scheduleClient:ScheduleAppointmentService) {
              this.appointmentId = this.router1.getCurrentNavigation()?.extras?.state?.['data']!
              this.userToken = this.tokenStorage.getUser()
  }

  ngOnInit(): void {
    const user = this.tokenStorage.getUser();
   this.questionnaire.customerId = user.id;
    this.getUserById(user.id!);
  }

  private getUserById(id: string) {
    this.customerClient.getCustomerById(id).subscribe({
      next: response => {
        this.applicationUser = response;
        console.log(this.applicationUser.gender);
        this.checkGender();
      }
    })
  }

  private checkGender() {
    if (this.applicationUser.gender === "FEMALE") {
      this.female = true;
    }
  }

  next(stepper:MatStepper){
    this.stepper = stepper;
    this.stepper.next();
  }
  onAnswer1(answer: string){
    console.log(answer);
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
      this.questionnaire.isSick = this.stringToBoolean(answer);
    }
  onAnswer11(answer: string){
    this.questionnaire.isUnderTherapy = this.stringToBoolean(answer);
  }
  onAnswer12(answer: string){
    this.questionnaire.isBloodPressureNormal = this.stringToBoolean(answer);
  }
  onAnswer13(answer: string){
    this.questionnaire.isDentis = this.stringToBoolean(answer);
  }
  onAnswer14(answer: string){
    this.questionnaire.isPiercingTattoo = this.stringToBoolean(answer);
  }
  onSubmitQuestionnaire(){
    this.checkAllFieldstoneSubmitting();
    if(this.enableSubmit){
      this.questionnaire.submissionDate = new Date();
      console.log(this.questionnaire)
      this.client.createQuestionnaire(this.questionnaire).subscribe({
        next: _ => {
          this.toast.success("You have successfully submitted your blood donor questionnaire!","Success");
          if(this.appointmentId!= ""){
              const id = this.userToken.id
              var sc = new ScheduleAppointmentRequest();
              sc.customer_id = id
              sc.appointment_id = this.appointmentId!
              this.scheduleClient.createAppointment(sc).subscribe({
                next: res=>{

                  this.toast.success("You have successfully scheduled appointment!","Success");
            }
              })
          }
          this.router.navigate(['/facilities'])
        }
      })
    }else {
      this.toast.error("All questions must be answered!","Error");
    }
  }

  private checkAllFieldstoneSubmitting() {
    if(this.applicationUser.gender === "MALE"){
      this.checkForMaleDonors();
    }else {
      this.checkForFemaleDonors();
    }
  }

  private checkForMaleDonors() {
    if (this.questionnaire.isSick !== undefined
      && this.questionnaire.isAllergic !== undefined
      && this.questionnaire.useMedication !== undefined
      && this.questionnaire.hadCancer !== undefined
      && this.questionnaire.hadTransfusion !== undefined
      && this.questionnaire.isSexual !== undefined
      && this.questionnaire.isAge !== undefined) {
      this.enableSubmit = true;
    }
  }

  private checkForFemaleDonors() {
    if (this.questionnaire.isSick !== undefined
      && this.questionnaire.isAllergic !== undefined
      && this.questionnaire.useMedication !== undefined
      && this.questionnaire.hadCancer !== undefined
      && this.questionnaire.hadTransfusion !== undefined
      && this.questionnaire.isSexual !== undefined
      && this.questionnaire.isPregnant !== undefined
      && this.questionnaire.onPeriod !== undefined
      && this.questionnaire.isAge !== undefined) {
      this.enableSubmit = true;
    }
  }

  stringToBoolean(answer : string){
    return answer === 'yes';
  }
}
