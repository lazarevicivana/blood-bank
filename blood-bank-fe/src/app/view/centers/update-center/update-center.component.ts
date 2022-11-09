import { Component, OnInit } from '@angular/core';
import {GoogleMapApiService} from "../../../services/googleMapApi.service";

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
  constructor(private mapLoader:GoogleMapApiService) { }

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
        console.log(mapsMouseEvent.latLng.lat())
        console.log(mapsMouseEvent.latLng.lng())
        this.longitude = mapsMouseEvent.latLng.lng();
        this.latitude = mapsMouseEvent.latLng.lat();
      })
    })
  }

}
