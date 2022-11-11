import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {CenterAdministrator} from "../../../model/CenterAdministrator";
import {CenterAdministratorService} from "../../../services/center-administrator.service";
import {waitForAsync} from "@angular/core/testing";


@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  public center: Center
  public admins: CenterAdministrator[] = []
  selectedAdmin: string = ""
  private map!: google.maps.Map;
  private infoWindow!: google.maps.InfoWindow;

  constructor(private centerService:CenterService,private mapLoader:GoogleMapApiService, private centerAdminService:CenterAdministratorService) {
    this.center = new Center();
  }

  ngOnInit(): void {
    this.handleMap();
    this.getAvailableAdmins();
  }

  createCenter() {
    this.centerService.createCenter(this.center).subscribe({
      next: response => {
        this.center.id = response.id
        console.log('respooooonse' + response.id)
        console.log(this.center.id)
        this.updateAdminCenter()

      }
    })
  }

  private updateAdminCenter() {
    console.log('Azuriiiranje')
    console.log('admin id:' + this.selectedAdmin)
    console.log('centar id:' + this.center.id)
    console.log('izraz:' + (this.center.id != null && this.selectedAdmin != null))
    if (this.center.id != null && this.selectedAdmin != null) {
      this.centerAdminService.updateAdminCenter(this.selectedAdmin, this.center.id).subscribe({
        next: response => {
          console.log(response)
        }
      })
    }
  }

  private getAvailableAdmins() {
    this.centerAdminService.getAvailableAdmins().subscribe({
      next: response => {
        this.admins = response;
      }})
  }

  private handleMap() {
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
        this.center.latitude = mapsMouseEvent.latLng.lat();
        const marker = new google.maps.Marker({
          position: mapsMouseEvent.latLng,
          map:  this.map,
        });

        this.infoWindow.open( this.map, marker);

      })
    })
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

}
