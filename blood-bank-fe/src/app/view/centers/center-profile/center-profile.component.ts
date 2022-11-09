import { Component, OnInit } from '@angular/core';
import {CenterService} from "../../../services/center.service";
import {Center} from "../../../model/Center";
import {GoogleMapApiService} from "../../../services/googleMapApi.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-center-profile',
  templateUrl: './center-profile.component.html',
  styleUrls: ['./center-profile.component.css']
})
export class CenterProfileComponent implements OnInit {
  public center: Center;
  private map!: google.maps.Map;
  private id:string = "ef81c6fc-bd01-4148-b460-b9f2eb7c53c3"
  constructor(private centerService:CenterService,private mapLoader:GoogleMapApiService,private readonly router:Router) {
    this.center = new Center();
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
      this.map.addListener("click", (mapsMouseEvent:any) => {
        console.log(mapsMouseEvent.latLng.lat())
        console.log(mapsMouseEvent.latLng.lng())
      })
    })
    loaded.then(()=>{
        this.getCenter()
      }
    )
  }
  public async updateCenter(): Promise<void>{
    await this.router.navigateByUrl('/update-center')
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
            title: '1',
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
