import {Component, Input, OnInit} from '@angular/core';
import {CenterAdministrator} from "../../../../model/CenterAdministrator";

@Component({
  selector: 'app-admin-form',
  templateUrl: './admin-form.component.html',
  styleUrls: ['./admin-form.component.css']
})
export class AdminFormComponent implements OnInit {
  @Input() admin: CenterAdministrator

  constructor() {
    this.admin = new CenterAdministrator();
  }

  ngOnInit(): void {
  }

}
