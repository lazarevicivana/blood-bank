import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../model/Center";

@Component({
  selector: 'app-blood-card',
  templateUrl: './blood-card.component.html',
  styleUrls: ['./blood-card.component.css']
})
export class BloodCardComponent implements OnInit {
  @Input() bloodType: string = ""
  @Input() bloodAmount: number = 0
  constructor() { }

  ngOnInit(): void {
  }

}
