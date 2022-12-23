import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import {AllCentersComponent} from "./view/centers/all-centers/all-centers.component"
import {CenterProfileComponent} from "./view/centers/center-profile/center-profile.component";
import {LoginComponent} from "./view/login/login.component";
import {UpdateCenterComponent} from "./view/centers/update-center/update-center.component";
import {AccountComponent} from "./view/account/account.component";
import {CreateCenterComponent} from "./view/centers/create-center/create-center.component";
import {AllUsersComponent} from "./view/all-users/all-users.component";
import {CenterAdminProfileComponent} from "./view/centers/center-admin-profile/center-admin-profile.component";
import {CreateAppointmentComponent} from "./view/appointment/create-appointment/create-appointment.component";
import {QuestionnaireComponent} from "./view/questionnaire/questionnaire.component";
import {AllDonorsComponent} from "./view/customer/all-donors/all-donors.component";
import {CenterDonorsComponent} from "./view/customer/center-donors/center-donors.component";
import {
  CustomerAppointmentCreateComponent
} from "./view/appointment/customer-appointment-create/customer-appointment-create.component";
import {
  CreateSystemAdminComponent
} from "./view/system-administrator/create-system-admin/create-system-admin.component";
import {ExaminationComponent} from "./view/examination/examination.component";
import {FirstLoginComponent} from "./components/first-login/first-login.component";
import {ManagerCalendarComponent} from "./view/manager-calendar/manager-calendar.component";
import {SignOutComponent} from "./components/sign-out/sign-out.component";
import {CenterAdminGuard} from "../guards/center-admin.guard";
import {CustomerGuard} from "../guards/customer.guard";
import {AdminGuard} from "../guards/admin.guard";
import {CustomerCalendarComponent} from "./view/customer-calendar/customer-calendar.component";


const routes: Routes = [
  { path: 'facilities', component: AllCentersComponent},
  { path: 'account', component: AccountComponent},
  { path: 'center-profile', component: CenterProfileComponent},
  { path: 'update-center', component: UpdateCenterComponent,canActivate:[CenterAdminGuard]},
  { path: 'create-center', component: CreateCenterComponent,canActivate:[AdminGuard]},
  { path: 'all-users', component: AllUsersComponent},
  { path:'',component:LoginComponent},
  { path:'admin-center-profile',component:CenterAdminProfileComponent,canActivate:[CenterAdminGuard]},
  { path:'add-appointment',component:CreateAppointmentComponent,canActivate:[CenterAdminGuard]},
  { path:'questionnaire', component: QuestionnaireComponent,canActivate:[CustomerGuard]},
  { path:'all-donors',component:AllDonorsComponent,canActivate:[AdminGuard]},
  { path:'center-donors',component:CenterDonorsComponent,canActivate:[CenterAdminGuard]},
  { path:'customer-appointment-create',component:CustomerAppointmentCreateComponent},
  { path:'system-admin-create',component:CreateSystemAdminComponent,canActivate:[AdminGuard]},
  { path:'examination',component:ExaminationComponent,canActivate:[CenterAdminGuard]},
  { path:'first-login',component:FirstLoginComponent},
  { path:'calendar',component:ManagerCalendarComponent,canActivate:[CenterAdminGuard]},
  { path:'customer-calendar',component:CustomerCalendarComponent,canActivate:[CustomerGuard]},
  { path:'sign-out',component:SignOutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
