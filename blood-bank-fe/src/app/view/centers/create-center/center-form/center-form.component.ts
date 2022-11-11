import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../../../model/Center";
import {GoogleMapApiService} from "../../../../services/googleMapApi.service";

@Component({
  selector: 'app-center-form',
  templateUrl: './center-form.component.html',
  styleUrls: ['./center-form.component.css']
})
export class CenterFormComponent implements OnInit {
  @Input() center: Center
  private map!: google.maps.Map;
  private infoWindow!: google.maps.InfoWindow;
  constructor(private mapLoader:GoogleMapApiService) {
    this.center = new Center();
  }

  ngOnInit(): void {
    this.handleMap();
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

}
