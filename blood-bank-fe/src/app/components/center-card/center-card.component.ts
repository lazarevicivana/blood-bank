import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../../model/Center";

@Component({
  selector: 'app-center-card',
  templateUrl: './center-card.component.html',
  styleUrls: ['./center-card.component.css']
})
export class CenterCardComponent implements OnInit {
  @Input() center = new Center();
  constructor() { }

  ngOnInit(): void {
  }

}
