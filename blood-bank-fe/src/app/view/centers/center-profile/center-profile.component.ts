import { Component, OnInit } from '@angular/core';
import {CenterService} from "../../../services/center.service";
import {Center} from "../../../model/Center";
import {Loader} from "@googlemaps/js-api-loader";

@Component({
  selector: 'app-center-profile',
  templateUrl: './center-profile.component.html',
  styleUrls: ['./center-profile.component.css']
})
export class CenterProfileComponent implements OnInit {
  public center: Center;
  private map?: google.maps.Map|null|google.maps.StreetViewPanorama;
  private id:string = "ef81c6fc-bd01-4148-b460-b9f2eb7c53c3"
  constructor(private centerService:CenterService) {
    this.center = new Center();
  }

  ngOnInit(): void {
    this.getCenter()
    let loader = new Loader(
      {
        apiKey:'AIzaSyAT7ERt8tnUqLdbZs-gnknNKuBoUnIMEOE'
      }
    )
    let myLatLng = { lat: -25.363, lng: 131.044 };
    loader.load().then(()=>{
      this.map =  new google.maps.Map(
        document.getElementById("map") as HTMLElement,{
          center: {lat: 51.23334,lng: 6.78},
          zoom: 6
        }
      )
      new google.maps.Marker({
        position: myLatLng,
        map: this.map,
        title: "Hello World!",
      });
    })
  }
  private getCenter(){
    this.centerService.getCenter(this.id).subscribe(
      {
        next: response => {
          this.center = response;
          console.log(response)
          new google.maps.Marker({
            position: {
              // @ts-ignore
              lat: this.center.latitude,
              // @ts-ignore
              lng: this.center.longitude
            },
            map: this.map,
            title: "Hello World!",
          });
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
