import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CenterProfileComponent} from "./center-profile/center-profile.component";
import {MaterialModule} from "../../material/material.module";
import {AllCentersComponent} from "./all-centers/all-centers.component";
import {NgImageSliderModule} from "ng-image-slider";
import {GoogleMapsModule} from "@angular/google-maps";
import { UpdateCenterComponent } from './update-center/update-center.component';
import {FormsModule} from "@angular/forms";
import {CreateCenterComponent} from "./create-center/create-center.component";
import { MatSelectModule } from '@angular/material/select';
import {MatExpansionModule} from "@angular/material/expansion";
import {CenterCardComponent} from "../../components/center-card/center-card.component";



@NgModule({
  declarations: [CenterProfileComponent, AllCentersComponent, UpdateCenterComponent, CreateCenterComponent,CenterCardComponent],
  exports: [
    CreateCenterComponent,
    CenterCardComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    NgImageSliderModule,
    GoogleMapsModule,
    FormsModule,
    MatSelectModule,
    MatExpansionModule
  ]
})
export class CenterViewModule { }
