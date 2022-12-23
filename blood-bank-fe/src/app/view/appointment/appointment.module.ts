import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialModule} from "../../material/material.module";
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS,MatMomentDateModule} from "@angular/material-moment-adapter";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {CommonComponentsModule} from "../../components/common-components.module";
import { CustomerAppointmentCreateComponent } from './customer-appointment-create/customer-appointment-create.component';
import {CenterViewModule} from "../centers/center-view.module";
import {MatCheckboxModule} from "@angular/material/checkbox";
@NgModule({
  declarations: [
    CreateAppointmentComponent,
    CustomerAppointmentCreateComponent
  ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        NgxMaterialTimepickerModule,
        MatMomentDateModule,
        CommonComponentsModule,
        CenterViewModule,
        MatCheckboxModule
    ],
  exports:[
    CreateAppointmentComponent
  ],
  providers: [
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } }
  ]
})
export class AppointmentModule { }
