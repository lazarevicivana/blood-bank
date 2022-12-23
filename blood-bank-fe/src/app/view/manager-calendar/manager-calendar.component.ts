import { Component, OnInit } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import {TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../model/User";
import {CenterAdminService} from "../../services/center-admin.service";
import {Center} from "../../model/Center";
import {AppointmentService} from "../../services/appointment.service";
import {Appointment} from "../../model/Appointment";
import {map} from "rxjs";
import * as moment from "moment/moment";
import { HttpErrorResponse } from '@angular/common/http';
import { addDays, startOfWeek, subDays } from 'date-fns';
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {ScheduleAppStaff} from "../../model/ScheduleAppStaff";

const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#0E4C92',
    secondary: '#cbcbd226',
  },
  green: {
    primary: '#0b6623',
    secondary: '#e8fde7',
  },
};
@Component({
  selector: 'app-manager-calendar',
  templateUrl: './manager-calendar.component.html',
  styleUrls: ['./manager-calendar.component.css']
})
export class ManagerCalendarComponent implements OnInit {
  viewDate: Date;
  appointments: CalendarEvent<{}>[] = [];
  appointmentsResponse: Appointment[] = [];
  dayStartHour = 6;
  dayEndHour = 24;
  hourSegmentHeight = 120;
  daysInWeek = 7;
  view: CalendarView = CalendarView.Week;
  viewDateEnd: Date;
  canClick:boolean = false
  private readonly user: User;
  private center: Center = new Center();
  selectedEvent: CalendarEvent<{ appointment: ScheduleAppStaff }> = {
    title: null as any,
    start: null as any,
    end: null as any,
    meta: null as any,
  };

  constructor(private tokenStorageService: TokenStorageService,private adminCenterService:CenterAdminService
              ,private appService:AppointmentService,private readonly scheduleAppointmentService:ScheduleAppointmentService) {
    this.viewDate = new Date();
    this.viewDateEnd = addDays(this.viewDate, 6);
    this.user = this.tokenStorageService.getUser();
  }
  ngOnInit(): void {
    this.getCenter()
  }
  async handleCurrent(): Promise<void> {
    this.viewDate = new Date();
    this.viewDateEnd = addDays(this.viewDate, 6);
    await new Promise(resolve => setTimeout(resolve, 100));
    this.paint()
  }

  async handlePrevious(): Promise<void> {
    this.viewDate = subDays(this.viewDate, 7);
    this.viewDateEnd = addDays(this.viewDate, 6);
    await new Promise(resolve => setTimeout(resolve, 100));
    this.paint()
  }

  async handleNext(): Promise<void> {
      this.viewDate = addDays(this.viewDate, 7);
      this.viewDateEnd = addDays(this.viewDate, 6);
      await new Promise(resolve => setTimeout(resolve, 100));
      this.paint()
  }
  onEventClick(event: any): void {
      this.canClick = true;
      this.selectedEvent = event.event;
  }
  createTitle(appointment: ScheduleAppStaff): string {
    return (
      'Appointment:'+ '\n'+
      'Date: '+
      appointment.date +
      '\n' +
      'Start time: '+
      appointment.startTime +
      '\n' +
      'Finish time: '+
      appointment.finishTime+
      '\n' +'Customer:'+ '\n'+
      'Username: '+
      appointment.customerUsername+
      '\n' +
      'Name: '+
      appointment.customerName+
      '\n' +
      'Surname: '+
      appointment.customerSurname
    );
  }
  private getCenter():void{
    this.adminCenterService.getCenterForAdmin(this.user.id!).subscribe(
      response => {
        this.center = response;
        this.getAppointmentsForCenter()
      }
    )
  }
  private getAppointmentsForCenter(){
    this.scheduleAppointmentService.getScheduledAppointmentForStaff(this.center.id!)
      .pipe(
        map((results: ScheduleAppStaff[]) => {
          return results.map((appointment: ScheduleAppStaff) => {
            return {
              title: this.createTitle(appointment),
              start: this.setTime(appointment.date!,appointment.startTime!),
              end: this.setTime(appointment.date!,appointment.finishTime!),
              meta: {
                appointment,
              },
            };
          });
        })
      )
      .subscribe(
        //@ts-ignore
        (response: CalendarEvent<{ appointment: Appointment }>[]) => {
          this.appointments = response;
          this.paint()
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
  }
  private setTime(selectedDate:Date ,selectedTime:string){
    let selectedTimeDate = moment(selectedDate)
    let selectedHours:number = Number(selectedTime.slice(0,2))
    let selectedMinute:number = Number(selectedTime.slice(3,5))
    selectedTimeDate.set({hour:selectedHours,minute:selectedMinute})
    return selectedTimeDate.toDate()
  }
  private paint(){
    let elems = document.querySelectorAll('div.cal-header span')
    let times = document.querySelectorAll('.cal-time')
    console.log(elems)
    for (let i = 0; i < elems.length; i++) {
      const element = elems[i] as HTMLElement
      element.style.color = 'red';
    }
    for (let i = 0; i < times.length; i++) {
      const element = times[i] as HTMLElement
      element.style.color = 'blue';
    }
  }


}
