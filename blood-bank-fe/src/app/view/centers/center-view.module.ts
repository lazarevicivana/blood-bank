import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CenterProfileComponent} from "./center-profile/center-profile.component";
import {MaterialModule} from "../../material/material.module";
import {AllCentersComponent} from "./all-centers/all-centers.component";
import {NgImageSliderModule} from "ng-image-slider";



@NgModule({
  declarations: [CenterProfileComponent,AllCentersComponent],
  imports: [
    CommonModule,
    MaterialModule,
    NgImageSliderModule
  ]
})
export class CenterViewModule { }
