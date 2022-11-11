import { Component, OnInit } from '@angular/core';
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {Center} from "../../../model/Center";
import {Router} from "@angular/router";
import {CenterService} from "../../../services/center.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-update-center',
  templateUrl: './update-center.component.html',
  styleUrls: ['./update-center.component.css']
})
export class UpdateCenterComponent implements OnInit {
  private map!: google.maps.Map;
  private infoWindow!: google.maps.InfoWindow;
  public longitude:number = 44.0165;
  public latitude:number = 21.0059;
  public center:Center;
  private centerId: string;
  constructor(private mapLoader:GoogleMapApiService, private router:Router, private centerService:CenterService,private toast: ToastrService) {
    this.center = new Center()
    this.centerId = this.router.getCurrentNavigation()?.extras?.state?.['centerId'];
  }

  ngOnInit(): void {
    const preloaded = this.mapLoader.googleApi.then(()=>{
      this.map =  new google.maps.Map(
        document.getElementById("map") as HTMLElement,{
          center: {lat: 44.0165,lng: 21.0059},
          zoom: 6
        }
      )
    }).catch(e => {
      console.log("Something goes wrong",e)
    });
    const myLatlng = { lat: 44.0165, lng: 21.0059 };
    // Create the initial InfoWindow.
    const loaded = preloaded.then(()=>{
       this.infoWindow = new google.maps.InfoWindow({
        content: "Click the map to get Lat/Lng!",
        position: myLatlng,
      });
    });
    loaded.then(()=>{
      this.infoWindow.open(this.map);
      this.map.addListener("click", (mapsMouseEvent:any) => {
        this.infoWindow.close();
        // Create a new InfoWindow.
        this.infoWindow = new google.maps.InfoWindow({
          position: mapsMouseEvent.latLng,
        });
        this.infoWindow.setContent(
          JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
        );
        this.infoWindow.open(this.map);
        this.center.longitude = mapsMouseEvent.latLng.lng();
        this.center.latitude  = mapsMouseEvent.latLng.lat();
      })
      this.getCenterById()
    })
  }
  getCenterById(){
      this.centerService.getCenter(this.centerId).subscribe(
        {
          next: value => {
            this.center = value;
            console.log(this.center)
            new google.maps.Marker(
              {
                position: {
                  lat: this.center.latitude,
                  lng: this.center.longitude
                },
                map: this.map,
                title: "Current lat/lng"
              }
            )
          }
        }
      )
  }

  updateCenter() {
    this.centerService.updateCenter(this.center).subscribe(
      {
        next: res => {
          this.toast.success("You are successfully update center!","Success")
          this.router.navigateByUrl('/admin-center-profile')
        },
        error: err => {
          this.toast.error(err.error.message,"Error")
        }
      }
    )
  }
}
