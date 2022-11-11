import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../model/Center";
import {AppointmentResponse} from "../../model/AppointmentResponse";

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.css']
})
export class AppointmentCardComponent implements OnInit {
  @Input() appointment = new AppointmentResponse();
  constructor() { }

  ngOnInit(): void {
  }

}
