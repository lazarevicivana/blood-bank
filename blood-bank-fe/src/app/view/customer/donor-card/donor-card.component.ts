import {Component, Input, OnInit} from '@angular/core';
import {UserResponse} from "../../../model/UserResponse";

@Component({
  selector: 'app-donor-card',
  templateUrl: './donor-card.component.html',
  styleUrls: ['./donor-card.component.css']
})
export class DonorCardComponent implements OnInit {
  @Input() user =  new UserResponse();
  constructor() { }

  ngOnInit(): void {
  }
}
