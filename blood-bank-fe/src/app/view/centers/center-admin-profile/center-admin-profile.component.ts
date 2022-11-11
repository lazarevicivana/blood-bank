import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {Router} from "@angular/router";
import {CenterAdminService} from "../../../services/center-admin.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {UserToken} from "../../../model/UserToken";
import {UserResponse} from "../../../model/UserResponse";
import {AppointmentService} from "../../../services/appointment.service";
import {AppointmentResponse} from "../../../model/AppointmentResponse";

@Component({
  selector: 'app-center-admin-profile',
  templateUrl: './center-admin-profile.component.html',
  styleUrls: ['./center-admin-profile.component.css']
})
export class CenterAdminProfileComponent implements OnInit {

  public center: Center;
  private map!: google.maps.Map;
  private readonly user: UserToken;
  public otherAdmins: UserResponse[] = [];
  public appointments: AppointmentResponse[] = [];
  constructor(private centerService:CenterService,private mapLoader:GoogleMapApiService,
              private readonly router:Router,private adminCenterService: CenterAdminService
              ,private appService:AppointmentService, private tokenStorageService: TokenStorageService ) {
    this.center = new Center();
    this.user = this.tokenStorageService.getUser();
    console.log(this.user)
  }
  ngOnInit(): void {
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
  public async updateCenter(): Promise<void>{
    await this.router.navigateByUrl('/update-center')
  }
  private getCenter():void{
    this.adminCenterService.getCenterForAdmin(this.user.id).subscribe(
        response => {
          this.center = response;
          new google.maps.Marker({
            position: {
              lat: this.center.latitude,
              lng: this.center.longitude
            },
            map: this.map,
            title: '1',
          });
          this.getOtherAdmins()
          this.getAppointmentsForCenter()
        }
    )
  }
  private getOtherAdmins():void{
    this.centerService.getOtherCenterAdmins(this.center.id!,this.user.id)
      .subscribe({
        next: response => {
          this.otherAdmins = response;
          console.log(this.otherAdmins)
        }
      })
  }
  private getAppointmentsForCenter(){
    this.appService.getAppointmentsForCenter(this.center.id!).subscribe(
      {
        next: response =>{
          this.appointments = response;
          console.log(this.appointments)
          // this.appointments.forEach( app =>{
          //   console.log(app.medicalStaffs?.forEach(st =>{
          //     console.log(st.name)
          //     console.log(st.surname)
          //   }))
          // })
        }
      }
    )
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
