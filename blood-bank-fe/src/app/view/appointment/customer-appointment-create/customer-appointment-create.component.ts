import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import * as moment from "moment/moment";
import {AppointmentService} from "../../../services/appointment.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../../services/token-storage.service";
import {UserToken} from "../../../model/UserToken";

@Component({
  selector: 'app-customer-appointment-create',
  templateUrl: './customer-appointment-create.component.html',
  styleUrls: ['./customer-appointment-create.component.css']
})
export class CustomerAppointmentCreateComponent implements OnInit {

  centers : Center[]= []
  centersFiltered : Center[]= []
  selectedDate: Date | undefined
  selectedTime:any
  selectedTimeDate:any
  selectedAppointmentId: string | undefined = ""
  public visable = false;
  private userToken: UserToken;
  constructor(private centerService:CenterService, private appointmentService:AppointmentService,
              private router:Router,private tkStorage:TokenStorageService) {
    this.userToken = this.tkStorage.getUser()
  }

  ngOnInit(): void {
  }

  public getAllCenters(){
    console.log(this.selectedDate)
    console.log(this.selectedTime)
    this.setTime()
    this.centerService.getCentersWithAppointment(this.selectedTimeDate).subscribe(response => {
      this.centers = response;
      console.log(response)
      this.visable = true;
    })
  }
  private setTime(){
    this.selectedTimeDate = moment(this.selectedDate)
    let selectedHours:number = Number(this.selectedTime.slice(0,2))
    let selectedMinute:number = Number(this.selectedTime.slice(3,5))
    this.selectedTimeDate.set({hour:selectedHours-1,minute:selectedMinute})
    console.log(this.selectedTimeDate)

  }

  schedule(selectedCenter: Center) {
      this.appointmentService.getWantedAppoontmentinCenter(this.selectedTimeDate,selectedCenter.id).subscribe(
        res => {
          this.selectedAppointmentId = res.id
          console.log(this.selectedAppointmentId)
          var id = this.userToken.user?.id
          this.router.navigate(['/questionnaire'],{state:{data:this.selectedAppointmentId}})
        }
      )
  }
}
