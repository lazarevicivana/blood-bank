import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Center} from "../../model/Center";
import {Router} from "@angular/router";

@Component({
  selector: 'app-center-card',
  templateUrl: './center-card.component.html',
  styleUrls: ['./center-card.component.css']
})
export class CenterCardComponent implements OnInit {
  @Input() center = new Center();
  @Input() isSchedule = false;
  @Output() onScheduleAppointment: EventEmitter<Center> = new EventEmitter<Center>();
  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  centerProfile(){
    this.router.navigate(['center-profile'], { state: { centerId: this.center.id } })
  }

  scheduleAppointmentINCenter(){
    this.onScheduleAppointment.emit(this.center)
  }

}
