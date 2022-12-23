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
import { addDays, subDays } from 'date-fns';
import {ScheduleAppointmentService} from "../../services/schedule-appointment.service";
import {ScheduleAppStaff} from "../../model/ScheduleAppStaff";
import {ExaminationService} from "../../services/examination.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

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
  hourSegmentHeight = 130;
  daysInWeek = 7;
  view: CalendarView = CalendarView.Week;
  viewDateEnd: Date;
  canClick:boolean = false
  canClickExamination:boolean = false
  private readonly user: User;
  private center: Center = new Center();
  monthView: boolean = false
  selectedEvent: CalendarEvent<{ appointment: ScheduleAppStaff }> = {
    title: null as any,
    start: null as any,
    color: { ...colors['blue'] },
    end: null as any,
    meta: null as any,
  };

  constructor(private tokenStorageService: TokenStorageService,private adminCenterService:CenterAdminService
              ,private appService:AppointmentService,
              private readonly scheduleAppointmentService:ScheduleAppointmentService,
              private readonly examinationService:ExaminationService,private router:Router,private ts:ToastrService) {
    this.viewDate = new Date();
    this.viewDateEnd = addDays(this.viewDate, 6);
    this.user = this.tokenStorageService.getUser();
  }
  ngOnInit(): void {
    this.fetchData()
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
      this.selectedEvent.color = colors['blue'];
      this.selectedEvent = event.event;
      if(this.selectedEvent.meta?.appointment.status === 'PENDING'){
        this.canClickExamination = true
      }
      console.log(event)
      this.selectedEvent.color = colors['green'];
  }
  createTitle(appointment: ScheduleAppStaff): string {
    return (
      'Appointment:'+ '\n'+
      'Status: '+
      appointment.status +'\n'+
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
  private fetchData():void{
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
              color: { ...colors['blue'] },
              meta: {
                appointment,
              },
            };
          });
        })
      )
      .subscribe(
        //@ts-ignore
        (response: CalendarEvent<{ appointment: ScheduleAppStaff }>[]) => {
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
    let elemsDays = document.querySelectorAll('div.cal-header b')
    console.log(elemsDays)
    let times = document.querySelectorAll('.cal-time')
    for (let i = 0; i < elems.length; i++) {
      const element = elems[i] as HTMLElement
      element.style.color = 'blue';
    }
    for (let i = 0; i < elemsDays.length; i++) {
      const element = elemsDays[i] as HTMLElement
      element.style.color = 'black';
    }
    for (let i = 0; i < times.length; i++) {
      const element = times[i] as HTMLElement
      element.style.color = '#3b4d79';
    }
  }


  goToExaminate() {
    // console.log("date",this.selectedEvent.meta?.appointment.date! > new Date())
    // if(this.selectedEvent.meta?.appointment.date! > new Date()){
    //   this.ts.error("You cannot examine appointment before start","Error")
    //   this.canClickExamination = false
    //   return
    // }
    this.examinationService.saveCurrentId(this.selectedEvent.meta?.appointment.id!)
    this.router.navigate(['examination'])
  }

  monthShow() {
    this.monthView  = !this.monthView;
  }
}
