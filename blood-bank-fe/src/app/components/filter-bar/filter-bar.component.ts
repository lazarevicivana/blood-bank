import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Center} from "../../model/Center";
import {CenterService} from "../../services/center.service";

@Component({
  selector: 'app-filter-bar',
  templateUrl: './filter-bar.component.html',
  styleUrls: ['./filter-bar.component.css']
})
export class FilterBarComponent implements OnInit {

  selectedName=""
  selectedCountry =""
  selectedCity =""
  centers : Center[]= []
  centersUnique : Center[]= []
  centersFilterdByCountry : Center[]= []


  @Output() onCountryFilter: EventEmitter<string> = new EventEmitter<string>();
  @Output() onCityFilter: EventEmitter<string> = new EventEmitter<string>();
  @Output() onNameFilter: EventEmitter<string> = new EventEmitter<string>();

  constructor(private centerService:CenterService) { }

  ngOnInit(): void {
    this.centerService.getAllCenters().subscribe(
      (response)=>{
        this.centersUnique = response.filter(
          (Center,i,arr)=> arr.findIndex(t=>t.country===Center.country)===i
        );
        this.centers = response;

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
        return obj.country === selectedCountry;})
      this.selectedCity=""

    }
    this.onCountryFilter.emit(selectedCountry)

  }

  selectCity(selectedCity: string) {
    this.onCityFilter.emit(selectedCity)
  }

  onNameChange() {
    this.onNameFilter.emit(this.selectedName)
  }

  clearName() {
    this.selectedName=""
    this.onNameFilter.emit("All")
  }
}
