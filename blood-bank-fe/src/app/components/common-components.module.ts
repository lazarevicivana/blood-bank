import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserCardComponent} from "./user-card/user-card.component";
import { AppointmentCardComponent } from './appointment-card/appointment-card.component';
import {MaterialModule} from "../material/material.module";
import { AllAdminsCenterComponent } from './all-admins-center/all-admins-center.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BloodDonorQuestionComponent } from './blood-donor-question/blood-donor-question.component';
import {MatRadioModule} from "@angular/material/radio";



@NgModule({
  declarations: [
    UserCardComponent,
    AppointmentCardComponent,
    AllAdminsCenterComponent,
    BloodDonorQuestionComponent
  ],
  imports: [
    CommonModule, MaterialModule, ReactiveFormsModule, MatRadioModule, FormsModule
  ],
  exports: [
    UserCardComponent, AppointmentCardComponent, AllAdminsCenterComponent, BloodDonorQuestionComponent
  ]

})
export class CommonComponentsModule { }
