import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CalendarViewComponent } from './calendar-view/calendar-view.component';
import {MaterialModule} from "../../material/material.module";

@NgModule({
  declarations: [
    CalendarViewComponent
  ],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports:[
    CalendarViewComponent
  ]
})
export class CalendarViewModule { }
