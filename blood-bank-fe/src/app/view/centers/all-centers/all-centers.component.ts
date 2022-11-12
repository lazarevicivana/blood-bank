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
  selectedCity="All"
  selectedName="All"
  selectedCountry="All"
  centers : Center[]= []
  centersFiltered : Center[]= []
  centersFilterdByCountry : Center[]= []
  centersFilterdByCity : Center[]= []
  centersFilterdByName : Center[]= []
  centersFilterdByGrade : Center[]= []
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
        this.centers = response;
        this.centersFiltered = response;
        this.centersFilterdByCountry = response;
        this.centersFilterdByCity = response;
        this.centersFilterdByName = response;
        this.centersFilterdByGrade = response;
        console.log(this.centers)
        this.centersFiltered.forEach((center)=>{
          new google.maps.Marker({
            position: {
              lat: center.latitude,
              lng: center.longitude
            },
            map: this.map,
            title: center.name
          });
        })
      },
    )
  }

  private filterMap(){
    this.centersFiltered.forEach((center)=> {
      new google.maps.Marker({
        position: {
          lat: center.latitude,
          lng: center.longitude
        },
        map: this.map,
        title: center.name
      });
    })
  }

  private filterCentersByAllFilters(){
    this.centersFiltered = this.centers.filter(e => {
      return this.centersFilterdByCountry.some(item => item.id === e.id);});
    this.centersFiltered = this.centersFiltered.filter(e => {
      return this.centersFilterdByCity.some(item => item.id === e.id);});
    this.centersFiltered = this.centersFiltered.filter(e => {
      return this.centersFilterdByName.some(item => item.id === e.id);});
    this.centersFiltered = this.centersFiltered.filter(e => {
      return this.centersFilterdByGrade.some(item => item.id === e.id);});
  }



  public getByCountryFilter(name: string){
    this.selectedCountry=name
    if(name != "All" && name!=""){
      this.centersFilterdByCity = this.centers
        this.centersFilterdByCountry = this.centers.filter((obj) => {
          return obj.country === name;
        });
    }else {
      this.centersFilterdByCountry = this.centers
      this.centersFilterdByCity=this.centers
    }
    this.filterCentersByAllFilters();
  }

  getByCityFilter(city: string) {
    this.selectedCity = city
    if(this.selectedCity!="All" && this.selectedCity!=""){
      this.centersFilterdByCity = this.centers.filter((obj) => {
        return obj.city === city;});
    }
    else {
      this.centersFilterdByCity = this.centers
    }
    this.filterCentersByAllFilters();
  }


  getByNameFilter(name: string) {
    this.selectedName = name
    if(name!="All" && name!=""){
      this.centersFilterdByName = this.centers.filter((obj) => {
        return obj.name?.toLowerCase().includes(name.toLowerCase())})
    }
    else {
      this.centersFilterdByName= this.centers
    }
    this.filterCentersByAllFilters();
  }
  sortCenters(sort: string){
    console.log(sort)
    if(sort === "nameAsc"){
      this.centersFiltered.sort((a, b) => (a.name?.toLowerCase()! > b.name?.toLowerCase()! ? 1 : -1));
    }else if(sort === "nameDesc"){
      this.centersFiltered.sort((a, b) => (a.name?.toLowerCase()! > b.name?.toLowerCase()! ? -1 : 1));
    }else if(sort === "cityAsc"){
      this.centersFiltered.sort((a, b) => (a.city?.toLowerCase()! > b.city?.toLowerCase()! ? 1 : -1));
    }else if(sort === "cityDesc"){
      this.centersFiltered.sort((a, b) => (a.city?.toLowerCase()! > b.city?.toLowerCase()! ? -1 : 1));
    }else if(sort === "gradeAsc"){
      this.centersFiltered.sort((a, b) => (a > b ? 1 : -1));
    }else if(sort === "gradeDesc"){
      this.centersFiltered.sort((a, b) => (a > b ? -1 : 1));
    }

  }

  getByGradeFilter(grade: string) {
    console.log(Number(grade))
    if (grade != "All" && grade!=""){

      this.centersFilterdByGrade = this.centers.filter((obj) => {
        return (Number(obj.avgGrade)>=Number(grade))
      });
    }
    else {

      this.centersFilterdByGrade= this.centers
    }
    this.filterCentersByAllFilters()
  }
}
