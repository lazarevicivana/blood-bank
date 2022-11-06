import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import { IonicModule } from "@ionic/angular";
import { AppRoutingModule } from "./app-routing.module";
import {HttpClientModule,HttpHeaders} from '@angular/common/http'
import {FormsModule} from "@angular/forms";

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { BodyComponent } from './components/body/body.component';
import { AllCentersComponent } from './view/all-centers/all-centers.component';
import {RouterLink, RouterOutlet} from "@angular/router";
import { AccountComponent } from './view/account/account.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    AllCentersComponent,
    BodyComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    IonicModule.forRoot(),
    RouterOutlet,
    RouterLink,
    AppRoutingModule,
    HttpClientModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
