import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {AllCentersComponent} from "./view/centers/all-centers/all-centers.component"
import {CenterProfileComponent} from "./view/centers/center-profile/center-profile.component";
import {LoginComponent} from "./view/login/login/login.component";
import {UpdateCenterComponent} from "./view/centers/update-center/update-center.component";
import {AccountComponent} from "./view/account/account.component";
import {CreateCenterComponent} from "./view/centers/create-center/create-center.component";
import {AllUsersComponent} from "./view/all-users/all-users.component";
import {CenterAdminProfileComponent} from "./view/centers/center-admin-profile/center-admin-profile.component";

const routes: Routes = [
  { path: 'facilities', component: AllCentersComponent },
  { path: 'account', component: AccountComponent },
  { path: 'center-profile', component: CenterProfileComponent},
  { path: 'update-center', component: UpdateCenterComponent},
  { path: 'create-center', component: CreateCenterComponent},
  { path: 'all-users', component: AllUsersComponent},
  { path:'',component:LoginComponent},
  { path:'admin-center-profile',component:CenterAdminProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
