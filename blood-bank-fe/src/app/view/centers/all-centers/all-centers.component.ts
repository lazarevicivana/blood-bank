import { Component, OnInit } from '@angular/core';
import {CenterService} from "../../../services/center.service";
import {Center} from "../../../model/Center";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";

@Component({
  selector: 'app-all-centers',
  templateUrl: './all-centers.component.html',
  styleUrls: ['./all-centers.component.css']
})
export class AllCentersComponent implements OnInit {
  centers : Center[]= []
  private map!: google.maps.Map;
  constructor(private centerService:CenterService,private mapLoader:GoogleMapApiService) { }

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
      this.getAllCenters()
      }
    )
    loaded.then()

  }
  private getAllCenters(){
    this.centerService.getAllCenters().subscribe(
      (response)=>{
        this.centers = response
        console.log(this.centers)
        this.centers.forEach((center)=>{
          new google.maps.Marker({
            position: {
              lat: center.latitude,
              lng: center.longitude
            },
            map: this.map,
            title:'1'
          });
        })
      },
    )
  }

}
