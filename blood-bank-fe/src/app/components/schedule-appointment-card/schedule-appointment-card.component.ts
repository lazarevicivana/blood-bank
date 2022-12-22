import {Component, Input, OnInit} from '@angular/core';
import {Appointment} from "../../model/Appointment";
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {ScheduleAppointmentRequest} from "../../model/Requests/ScheduleAppointmentRequest";
import {TokenStorageService} from "../../services/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-schedule-appointment-card',
  templateUrl: './schedule-appointment-card.component.html',
  styleUrls: ['./schedule-appointment-card.component.css']
})
export class ScheduleAppointmentCardComponent implements OnInit {
  @Input() appointment = new Appointment();
  constructor(private readonly router: Router,private readonly client : ScheduleAppointmentService,private readonly tokenStorage: TokenStorageService, private readonly toast: ToastrService ) { }

  ngOnInit(): void {
  }
  onSchedule(){
      const scheduleAppointmentRequest = new ScheduleAppointmentRequest({
        appointment_id : this.appointment.id,
        customer_id : this.tokenStorage.getUser().id
      })
      this.client.createAppointment(scheduleAppointmentRequest).subscribe({
        next: _=>{
          this.toast.success("You have successfully scheduled an appointment!","Success")
          this.router.navigate(['/facilities']).then();
        },
        error: err => {
          this.toast.warning(err.error.message,"Warning")
        }
      })
  }

}
