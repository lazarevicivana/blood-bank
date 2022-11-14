import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../../services/token-storage.service";
import {UserToken} from "../../../model/UserToken";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {CenterAdministratorService} from "../../../services/center-administrator.service";
import {Center} from "../../../model/Center";
import * as moment from "moment/moment";
import {AppointmentService} from "../../../services/appointment.service";
import {Appointment} from "../../../model/Appointment";
import {AppointmentRequest} from "../../../model/AppointmentRequest";
import {Router} from "@angular/router";
@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {
  private userToken:UserToken;
  formGroup = new FormGroup({
    date: new FormControl<Date | undefined>(new Date(),[Validators.required]),
    startTime:new FormControl<string | undefined>(undefined,Validators.required),
    finishTime:new FormControl<string | undefined>(undefined,Validators.required),
  });
  private staffsId: string[] = []
  public isLoaded:boolean = false;
  public center: Center;
  constructor(private tokenService:TokenStorageService,private toast:ToastrService
              ,private adminService: CenterAdministratorService,private appService: AppointmentService,private router: Router) {
    this.userToken = tokenService.getUser()
    this.center = new Center();
  }
  ngOnInit(): void {
    this.getCenter()
  }
  private getCenter(){
    this.adminService.getCenterForAdmin(this.userToken.user?.id!).subscribe(
      {
        next: res =>{
          this.center = res;
          this.isLoaded =true;
        }
      }
    )
  }

  createAppointment() {
    console.log(this.formGroup.controls.date.value)
    console.log(this.formGroup.controls.startTime.value)
    console.log(this.formGroup.controls.finishTime.value)
    let app: AppointmentRequest = new AppointmentRequest()
    app.date = this.formGroup.controls.date.value!;
    app.startTime = this.formGroup.controls.startTime.value!
    app.finishTime = this.formGroup.controls.finishTime.value!
    app.medicalStaffs = this.staffsId;
    app.centerId = this.center.id;
    this.appService.createAppointment(app).subscribe(
      {
        next: res => {
          console.log(res)
          this.toast.success("You are successfully crated an appointment!","Success")
          this.router.navigateByUrl('/admin-center-profile')
        },
        error: err => {
          this.toast.error(err.error.message,"Error")
        }
      }
    )

  }

  loadStaff(event: any) {
    this.staffsId = event;
  }
}
