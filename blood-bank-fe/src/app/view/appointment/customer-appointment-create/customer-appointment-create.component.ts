import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";

@Component({
  selector: 'app-customer-appointment-create',
  templateUrl: './customer-appointment-create.component.html',
  styleUrls: ['./customer-appointment-create.component.css']
})
export class CustomerAppointmentCreateComponent implements OnInit {

  centers : Center[]= []
  centersFiltered : Center[]= []
  public visable = false;
  constructor(private centerService:CenterService) { }

  ngOnInit(): void {
  }

  public getAllCenters(){
    this.centerService.getAllCenters().subscribe(response => {
      this.centers = response;
      this.visable = true;
    })
  }

}
