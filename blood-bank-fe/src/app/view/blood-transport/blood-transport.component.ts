import { Component, OnInit } from '@angular/core';
import {BloodTransportService} from "../../services/blood-transport.service";
import {Coordinate} from "../../model/coordinate.model";
import {GoogleMapApiService} from "../../services/googleMapApi.service";

@Component({
  selector: 'app-blood-transport',
  templateUrl: './blood-transport.component.html',
  styleUrls: ['./blood-transport.component.css']
})
export class BloodTransportComponent implements OnInit {
coordinates = new Coordinate();
private map!: google.maps.Map;
marker = new google.maps.Marker();
  constructor(private readonly client: BloodTransportService,private mapLoader:GoogleMapApiService) { }

  ngOnInit(): void {
    /*this.recursiveFunction()*/
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
      this.client.startTransport().subscribe({
        next: response => {
          console.log(response)
          this.recursiveFunction()
        }
      })
    })

  }
  getMarker(){
    const marker = new google.maps.Marker({
      position:  {lat: 44.0165,lng: 21.0059},
      map: this.map,
    });
  }
   recursiveFunction() {
    // Do something here
    this.client.getCoordinates().subscribe({
      next: response => {
        this.coordinates = response;
        console.log(response)
        const position = {
          lat: this.coordinates.latitude!,
          lng: this.coordinates.longitude!
        }
        this.marker.setPosition(position)
        this.marker.setMap(this.map)
      }
    })
    setTimeout(() => this.recursiveFunction(), 10000);
  }

}
