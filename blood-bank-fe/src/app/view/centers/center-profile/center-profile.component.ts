import { Component, OnInit } from '@angular/core';
import {CenterService} from "../../../services/center.service";
import {Center} from "../../../model/Center";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../../services/token-storage.service";
import {Appointment} from "../../../model/Appointment";
import {AppointmentService} from "../../../services/appointment.service";
import {User} from "../../../model/User";

@Component({
  selector: 'app-center-profile',
  templateUrl: './center-profile.component.html',
  styleUrls: ['./center-profile.component.css']
})
export class CenterProfileComponent implements OnInit {
  public center: Center;
  user  = new User("","",false);
  public appointments: Appointment[] = [];
  filterAppointments : Appointment[] = [];
  sortDate = 'sort';
  sortTime = 'sort';
  private map!: google.maps.Map;
  private id:string = "ef81c6fc-bd01-4148-b460-b9f2eb7c53c3"
  constructor(private readonly tokenStorage: TokenStorageService, private centerService:CenterService,private mapLoader:GoogleMapApiService, private appService:AppointmentService, private readonly router:Router) {
    this.id = this.router.getCurrentNavigation()?.extras?.state?.['centerId']!
    this.center = new Center();
  }
  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    const loaded = this.mapLoader.googleApi.then(()=>{
      this.map =  new google.maps.Map(
        document.getElementById("map") as HTMLElement,{
          center: {lat: 44.0165,lng: 21.0059},
          zoom: 6
        }
      )
    }).catch(e => {
      console.log("Something goes wrong",e)
    });
    loaded.then(()=>{
        this.getCenter()
      }
    )
  }
  private getCenter(){
    this.centerService.getCenter(this.id).subscribe(
      {
        next: response => {
          this.center = response;
          console.log(response)
          new google.maps.Marker({
            position: {
              lat: this.center.latitude,
              lng: this.center.longitude
            },
            map: this.map,
            title: this.center.name,
          });
          this.getAppointmentsForCenter()
          this.getFutureAppointmentsCenter()

        }
      }
    )
  }
  private getFutureAppointmentsCenter(){
    this.appService.getFutureAppointmentForCenter(this.center.id!).subscribe(
      {
        next: response =>{
          this.filterAppointments = response;
          console.log(this.filterAppointments)
        }
      }
    )
  }
  private getAppointmentsForCenter(){
    this.appService.getAppointmentsForCenter(this.center.id!).subscribe(
      {
        next: response =>{
          this.appointments = response;
        }
      }
    )
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
  imgCollection: Array<object> = [
    {
      image: 'assets/bank1-1.jpg',
      thumbImage: 'assets/bank1-1.jpg',
      alt: 'Image 1',
      title: 'Image 1'
    }, {
      image: 'assets/bank1-1.jpg',
      thumbImage: 'assets/bank1.jpg',
      title: 'Image 2',
      alt: 'Image 2'
    },
    {
      image: 'assets/bank1-2.jpg',
      thumbImage: 'assets/bank1-2.jpg',
      title: 'Image 3',
      alt: 'Image 3'
    }
  ];
}
