import { Component, OnInit } from '@angular/core';
import {BloodRequest} from "../../../model/Responses/BloodRequest";
import {BloodType} from "../../../model/BloodType";

@Component({
  selector: 'app-blood-requests',
  templateUrl: './blood-requests.component.html',
  styleUrls: ['./blood-requests.component.css']
})
export class BloodRequestsComponent implements OnInit {

  constructor() { }
  requests:BloodRequest[] = []
  ngOnInit(): void {
    var bloodReqest: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },

      ]
    }
    var bloodReqest1: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },

      ]
    }
    var bloodReqest2: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
      ]
    }
    var bloodReqest3: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },

      ]
    }
    var bloodReqest4: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },

      ]
    }
    var bloodReqest5: BloodRequest = {
      dateOfDelivery: new Date,
      hospitalName: "Healthy hospital",
      bloodUnits: [
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },
        {
          id: '1',
          bloodAmount: 50,
          bloodType: BloodType.VALUE3,
        },
        {
          id: '2',
          bloodAmount: 30,
          bloodType: BloodType.VALUE1,
        },

      ]
    }
    var newRequests:BloodRequest[] = [bloodReqest,bloodReqest1,bloodReqest2,bloodReqest4,bloodReqest5,bloodReqest3,bloodReqest5,bloodReqest
    ,bloodReqest3,bloodReqest2,bloodReqest4,bloodReqest1]
    this.requests = newRequests
  }
}
