import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialModule} from "../../material/material.module";
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS,MatMomentDateModule} from "@angular/material-moment-adapter";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {CommonComponentsModule} from "../../components/common-components.module";
@NgModule({
  declarations: [
    CreateAppointmentComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgxMaterialTimepickerModule,
    MatMomentDateModule,
    CommonComponentsModule
  ],
  exports:[
    CreateAppointmentComponent
  ],
  providers: [
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } }
  ]
})
export class AppointmentModule { }
