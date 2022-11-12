import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../model/Center";
import {Appointment} from "../../model/Appointment";

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.css']
})
export class AppointmentCardComponent implements OnInit {
  @Input() appointment = new Appointment();
  constructor() { }

  ngOnInit(): void {
  }

}
