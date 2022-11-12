import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {QuestionnaireComponent} from "./questionnaire.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatRadioModule} from "@angular/material/radio";
import {MatDividerModule} from "@angular/material/divider";
import {NgToastModule} from "ng-angular-popup";
import {CommonComponentsModule} from "../../components/common-components.module";
import {MatButtonModule} from "@angular/material/button";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations:
    [
      QuestionnaireComponent
    ],
  imports: [
    CommonModule,
    MatStepperModule,
    MatRadioModule,
    MatDividerModule,
    NgToastModule,
    CommonComponentsModule,
    MatButtonModule,
    ReactiveFormsModule
  ]
})
export class QuestionnaireModule { }
