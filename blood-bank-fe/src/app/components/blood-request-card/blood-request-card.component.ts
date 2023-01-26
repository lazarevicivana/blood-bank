import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
  @Output() onMakeOffer: EventEmitter<BloodRequest> = new EventEmitter<BloodRequest>();
  constructor() { }

  ngOnInit(): void {
  }

  makeOffer() {
    this.onMakeOffer.emit(this.bloodReqest)
  }
}
