import { Component, OnInit } from '@angular/core';
import {Appointment} from "../../../model/Appointment";
import {ScheduleAppCustomer} from "../../../model/ScheduleAppCustomer";
import {ScheduleAppointmentService} from "../../../services/schedule-appointment.service";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
  selector: 'app-center-visitation-history',
  templateUrl: './center-visitation-history.component.html',
  styleUrls: ['./center-visitation-history.component.css']
})
export class CenterVisitationHistoryComponent implements OnInit {
  public appointments: ScheduleAppCustomer[] = [];
  filterAppointments : ScheduleAppCustomer[] = [];
  sortDate = 'sort';
  sortTime = 'sort';
  constructor(private readonly client: ScheduleAppointmentService,private readonly token: TokenStorageService) { }

  ngOnInit(): void {

    this.client.getPassedScheduledAppointmentForCustomer(this.token.getUser().id).subscribe({
      next: response => {
        this.appointments = response
        this.filterAppointments = response
        console.log(this.appointments)
      }
    })
  }
  sortAppointments(sort: string){
    this.sortDate = sort;
    if(sort === "dateAsc" && this.sortTime ==='timeAsc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? 1 : -1
        } else {
          return a.date! > b.date! ? 1 : -1
        }
      });
    } else if(sort === "dateDesc" && this.sortTime ==='timeAsc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? 1 : -1
        } else {
          return a.date! > b.date! ? -1 : 1
        }
      });
    }else if(sort === "dateAsc" && this.sortTime ==='timeDesc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? -1 : 1
        } else {
          return a.date! > b.date! ?  1 : -1
        }
      });
    }
    else if(sort === "dateDesc" && this.sortTime ==='timeDesc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? -1 : 1
        } else {
          return a.date! > b.date! ? -1 : 1
        }
      });
    }
    else if(sort === "dateDesc" && this.sortTime ==='sort'){
      this.filterAppointments.sort((a, b) =>  (a.date! > b.date! ? -1 : 1));
    }else if(sort === "dateAsc" && this.sortTime ==='sort'){
      this.filterAppointments.sort((a, b) =>  (a.date! > b.date! ? 1 : -1));
    }
  }
  sortAppointmentsTime(sort: string){
    this.sortTime = sort
    if(sort === "timeAsc" && this.sortDate ==='dateAsc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? 1 : -1
        } else {
          return a.date! > b.date! ? 1 : -1
        }
      });
    }
    else if(sort === "timeAsc" && this.sortDate ==='dateDesc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? 1 : -1
        } else {
          return a.date! > b.date! ? -1 : 1
        }
      });
    }else if(sort === "timeDesc" && this.sortDate ==='dateAsc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? -1 : 1
        } else {
          return a.date! > b.date! ?  1 : -1
        }
      });
    }
    else if(sort === "timeDesc" && this.sortDate ==='dateDesc'){
      this.filterAppointments.sort((a, b) => {
        if (a.date === b.date) {
          return a.startTime! > b.startTime! ? -1 : 1
        } else {
          return a.date! > b.date! ? -1 : 1
        }
      });
    }
    else if(sort === "timeDesc" && this.sortDate ==='sort'){
      this.filterAppointments.sort((a, b) =>  (a.startTime! > b.startTime! ? -1 : 1));
    }else if(sort === "timeAsc" && this.sortDate ==='sort'){
      this.filterAppointments.sort((a, b) =>  (a.startTime! > b.startTime! ? 1 : -1));
    }
  }

}
