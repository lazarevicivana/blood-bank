import {Component, Input, OnInit} from '@angular/core';
import {BloodRequest} from "../../model/Responses/BloodRequest";
import {BloodType} from "../../model/BloodType";
import {UserResponse} from "../../model/Responses/UserResponse";

@Component({
  selector: 'app-blood-request-card',
  templateUrl: './blood-request-card.component.html',
  styleUrls: ['./blood-request-card.component.css']
})
export class BloodRequestCardComponent implements OnInit {
  @Input() bloodReqest =  new BloodRequest();

  constructor() { }

  ngOnInit(): void {
  }

}
