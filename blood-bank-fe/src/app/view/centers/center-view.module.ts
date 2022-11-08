import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CenterProfileComponent} from "./center-profile/center-profile.component";
import {MaterialModule} from "../../material/material.module";
import {AllCentersComponent} from "./all-centers/all-centers.component";
import {NgImageSliderModule} from "ng-image-slider";
import {GoogleMapsModule} from "@angular/google-maps";



@NgModule({
  declarations: [CenterProfileComponent,AllCentersComponent],
  imports: [
    CommonModule,
    MaterialModule,
    NgImageSliderModule,
    GoogleMapsModule
  ]
})
export class CenterViewModule { }
