import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import { IonicModule } from "@ionic/angular";
import { AppRoutingModule } from "./app-routing.module";


import { AppComponent } from './app.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { BodyComponent } from './components/body/body.component';
import { AllCentersComponent } from './view/all-centers/all-centers.component';
import {RouterLink, RouterOutlet} from "@angular/router";
import { AccountComponent } from './view/account/account.component';


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
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
