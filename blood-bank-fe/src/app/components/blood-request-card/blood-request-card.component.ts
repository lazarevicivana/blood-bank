import {Component, OnInit} from '@angular/core';
import {BloodRequest} from "../../model/Responses/BloodRequest";
import {BloodType} from "../../model/BloodType";

@Component({
  selector: 'app-blood-request-card',
  templateUrl: './blood-request-card.component.html',
  styleUrls: ['./blood-request-card.component.css']
})
export class BloodRequestCardComponent implements OnInit {

  bloodReqest:BloodRequest = {
    dateOfDelivery : new Date,
    hospitalName : "Healthy hospital",
    bloodUnits: [
      {
        id:'1',
        bloodAmount: 50,
        bloodType:BloodType.VALUE3,
      },
      {
        id:'2',
        bloodAmount: 30,
        bloodType : BloodType.VALUE1,
      },
    ]
  }
  constructor() { }

  ngOnInit(): void {
  }

}
