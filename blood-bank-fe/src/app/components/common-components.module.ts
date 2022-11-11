import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserCardComponent} from "./user-card/user-card.component";

import {FormsModule} from "@angular/forms";
import {MatOptionModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatIconModule} from "@angular/material/icon";



@NgModule({
  declarations: [UserCardComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatOptionModule,
    MatSelectModule,
    MatIconModule
  ],
  exports: [
    UserCardComponent,

  ]

})
export class CommonComponentsModule { }
