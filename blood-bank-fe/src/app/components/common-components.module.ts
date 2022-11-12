import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserCardComponent} from "./user-card/user-card.component";
import { AppointmentCardComponent } from './appointment-card/appointment-card.component';
import {MaterialModule} from "../material/material.module";
import { AllAdminsCenterComponent } from './all-admins-center/all-admins-center.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [UserCardComponent, AppointmentCardComponent, AllAdminsCenterComponent],
  imports: [
    CommonModule, MaterialModule, ReactiveFormsModule
  ],
  exports: [
    UserCardComponent, AppointmentCardComponent, AllAdminsCenterComponent
  ]

})
export class CommonComponentsModule { }
