import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../model/Center";
import {Appointment} from "../../model/Appointment";
import {TokenStorageService} from "../../services/token-storage.service";
import {ApplicationUser} from "../../model/ApplicationUser";

@Component({
  selector: 'app-appointment-card',
  templateUrl: './appointment-card.component.html',
  styleUrls: ['./appointment-card.component.css']
})
export class AppointmentCardComponent implements OnInit {
  @Input() appointment = new Appointment();
  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.token.getUser();
  }

}
