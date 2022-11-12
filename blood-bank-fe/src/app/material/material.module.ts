import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatListModule} from "@angular/material/list";
import {MatStepperModule} from "@angular/material/stepper";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatSelectModule} from "@angular/material/select";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatDividerModule} from "@angular/material/divider";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatTooltipModule,
    MatFormFieldModule,
    MatListModule,
    MatStepperModule,
    MatDatepickerModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatExpansionModule,
    MatGridListModule,
    MatDividerModule,
  ],
  exports: [
    CommonModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatTooltipModule,
    MatFormFieldModule,
    MatListModule,
    MatStepperModule,
    MatMomentDateModule,
    MatSelectModule,
    MatExpansionModule,
    MatGridListModule,
    MatDividerModule,
    MatDatepickerModule
  ]
})
export class MaterialModule {}
