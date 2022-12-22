import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-appointment-filter-bar',
  templateUrl: './appointment-filter-bar.component.html',
  styleUrls: ['./appointment-filter-bar.component.css']
})
export class AppointmentFilterBarComponent implements OnInit {
  sort : string[] =['dateAsc','dateDesc','timeAsc','timeDesc','sort']
  selectedSort = '';
  selectedSortTime='';
  @Output() onSortFilter: EventEmitter<string> = new EventEmitter<string>();
  @Output() onSortTimeFilter: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }
  selectSort(selectedSort : string){
    this.onSortFilter.emit(selectedSort);
  }
  selectSortTime(selectedSortTime : string){
    this.onSortTimeFilter.emit(selectedSortTime);

  }
}
