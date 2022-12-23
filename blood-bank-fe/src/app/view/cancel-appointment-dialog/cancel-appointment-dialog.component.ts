import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {Route, Router} from "@angular/router";
import {NgToastService} from "ng-angular-popup";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-cancel-appointment-dialog',
  templateUrl: './cancel-appointment-dialog.component.html',
  styleUrls: ['./cancel-appointment-dialog.component.css']
})
export class CancelAppointmentDialogComponent implements OnInit {
  appointmentId : string ='';
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,private readonly client:ScheduleAppointmentService,private readonly router: Router,private readonly toast: ToastrService) { }

  ngOnInit(): void {
    this.appointmentId = this.data.appointmentId;
  }
  onCancel(){
    this.client.cancelScheduledAppointment(this.appointmentId).subscribe({
      next: _=> {
       this.reloadPage()
      },
      error: err => {
        this.toast.error(err.error.message,"Error")
      }
    })
  }
  reloadPage(){
    window.location.reload();
  }

}
