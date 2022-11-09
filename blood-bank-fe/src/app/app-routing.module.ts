import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {AllCentersComponent} from "./view/centers/all-centers/all-centers.component"
import { AccountComponent } from './view/account/account.component';
import {CenterProfileComponent} from "./view/centers/center-profile/center-profile.component";
import {LoginComponent} from "./view/login/login/login.component";
import {UpdateCenterComponent} from "./view/centers/update-center/update-center.component";

const routes: Routes = [
  { path: 'facilities', component: AllCentersComponent },
  { path: 'account', component: AccountComponent },
  { path: 'center-profile', component: CenterProfileComponent},
  { path: 'update-center', component: UpdateCenterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
