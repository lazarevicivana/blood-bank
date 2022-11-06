import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {AllCentersComponent} from "./view/all-centers/all-centers.component"
import {AccountComponent} from "./view/account/account.component"

const routes: Routes = [
  { path: 'facilities', component: AllCentersComponent },
  { path: 'account', component: AccountComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
