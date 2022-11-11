import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Center} from "../../model/Center";
import {CenterService} from "../../services/center.service";

@Component({
  selector: 'app-filter-bar',
  templateUrl: './filter-bar.component.html',
  styleUrls: ['./filter-bar.component.css']
})
export class FilterBarComponent implements OnInit {

  selectedCountry =""
  selectedCity =""
  centers : Center[]= []
  centersFiltered : Center[]= []
  centersFilterdByCountry : Center[]= []
  centersFilterdByCity : Center[]= []

  @Output() onCountryFilter: EventEmitter<string> = new EventEmitter<string>();
  @Output() onCityFilter: EventEmitter<string> = new EventEmitter<string>();

  constructor(private centerService:CenterService) { }

  ngOnInit(): void {
    this.centerService.getAllCenters().subscribe(
      (response)=>{
        this.centers = response;
        this.centersFiltered = response;
        this.centersFilterdByCity = response;
        this.centersFilterdByCountry = response;
      });
  }

  selectCountry(selectedCountry: string) {
    if(selectedCountry ==="All"){
      this.centersFilterdByCountry = this.centers
      this.selectedCity=""
    }
    else{
      this.centersFilterdByCountry = this.centers.filter((obj) => {
        return obj.country === selectedCountry;});
      this.selectedCity=""
    }
    this.onCountryFilter.emit(selectedCountry)

  }

  selectCity(selectedCity: string) {
    this.onCityFilter.emit(selectedCity)
  }
}
