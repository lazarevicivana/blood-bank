import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blood-requests',
  templateUrl: './blood-requests.component.html',
  styleUrls: ['./blood-requests.component.css']
})
export class BloodRequestsComponent implements OnInit {

  constructor() { }
  requests:number[] = [1,2,3,4,5,5,6,7,8,1,9,4,6,1,8,9]
  ngOnInit(): void {
  }

}
