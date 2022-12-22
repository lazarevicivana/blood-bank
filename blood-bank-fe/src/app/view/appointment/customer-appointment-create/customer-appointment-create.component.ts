import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import * as moment from "moment/moment";

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
  public visable = false;
  constructor(private centerService:CenterService) { }

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
}
