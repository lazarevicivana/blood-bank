import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserCardComponent} from "./user-card/user-card.component";
import { AppointmentCardComponent } from './appointment-card/appointment-card.component';
import {MaterialModule} from "../material/material.module";



@NgModule({
  declarations: [UserCardComponent, AppointmentCardComponent],
  imports: [
    CommonModule,MaterialModule
  ],
  exports: [
    UserCardComponent,AppointmentCardComponent
  ]

})
export class CommonComponentsModule { }
