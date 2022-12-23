import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {Router} from "@angular/router";
import {CenterAdminService} from "../../../services/center-admin.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {UserResponse} from "../../../model/Responses/UserResponse";
import {AppointmentService} from "../../../services/appointment.service";
import {Appointment} from "../../../model/Appointment";
import {User} from "../../../model/User";
import {BloodBankService} from "../../../services/blood-bank.service";
import {BloodBank} from "../../../model/BloodBank";

@Component({
  selector: 'app-center-admin-profile',
  templateUrl: './center-admin-profile.component.html',
  styleUrls: ['./center-admin-profile.component.css']
})
export class CenterAdminProfileComponent implements OnInit {

  public center: Center;
  private map!: google.maps.Map;
  private readonly user: User;
  public otherAdmins: UserResponse[] = [];
  public appointments: Appointment[] = [];
  public banks:BloodBank[] =[];
  constructor(private centerService:CenterService,private mapLoader:GoogleMapApiService,
              private readonly router:Router,private adminCenterService: CenterAdminService
              ,private appService:AppointmentService, private tokenStorageService: TokenStorageService
              ,private readonly bankService:BloodBankService) {
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
    console.log(this.center.id)
    await this.router.navigate(['update-center'], { state: { centerId: this.center.id } })
  }
  private getCenter():void{
    this.adminCenterService.getCenterForAdmin(this.user.id!).subscribe(
        response => {
          this.center = response;
          new google.maps.Marker({
            position: {
              lat: this.center.latitude,
              lng: this.center.longitude
            },
            map: this.map,
            title: this.center.name,
          });
          this.getOtherAdmins()
          this.getAppointmentsForCenter()
          this.getBanks()
        }
    )
  }
  private getOtherAdmins():void{
    this.centerService.getOtherCenterAdmins(this.center.id!,this.user.id!)
      .subscribe({
        next: response => {
          this.otherAdmins = response;
          //console.log(this.otherAdmins)
        }
      })
  }
  private getBanks():void{
    this.bankService.getBanksForCenter(this.center.id!).subscribe({
      next: res =>{
        this.banks = res;
      }
    })
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
