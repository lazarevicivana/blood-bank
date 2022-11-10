import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CenterProfileComponent} from "./center-profile/center-profile.component";
import {MaterialModule} from "../../material/material.module";
import {AllCentersComponent} from "./all-centers/all-centers.component";
import {NgImageSliderModule} from "ng-image-slider";
import {GoogleMapsModule} from "@angular/google-maps";
import { UpdateCenterComponent } from './update-center/update-center.component';
import {FormsModule} from "@angular/forms";
import {AppModule} from "../../app.module";
import {CenterCardComponent} from "../../components/center-card/center-card.component";



@NgModule({
  declarations: [CenterProfileComponent,AllCentersComponent, UpdateCenterComponent,CenterCardComponent],
  imports: [
    CommonModule,
    MaterialModule,
    NgImageSliderModule,
    GoogleMapsModule,
    FormsModule
  ],
  exports:[
    CenterCardComponent
  ]
})
export class CenterViewModule { }
