import { Component, OnInit } from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {CenterAdministratorService} from "../../../services/center-administrator.service";
import {CenterAdministrator} from "../../../model/CenterAdministrator";

@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  public center: Center
  public admin: CenterAdministrator

  constructor(private centerService:CenterService, private centerAdminService:CenterAdministratorService) {
    this.center = new Center();
    this.admin = new CenterAdministrator()
  }

  ngOnInit(): void {
  }

  createCenter() {
   this.centerService.createCenter(this.center).subscribe({
      next: response => {
        this.center.id = response.id
        this.admin.center = this.center.id
      }
    })
  }

  createAdmin() {
    this.centerAdminService.createAdmin(this.admin).subscribe({
      next: response => {
        this.admin.username="";
        this.admin.name="";
        this.admin.surname="";
        this.admin.password="";
        this.admin.email="";
        this.admin.phone="";
        this.admin.jmbg="";
        this.admin.gender="";
        this.admin.username="";
        this.admin.country="";
        this.admin.street="";
        this.admin.city="";
        this.admin.streetNumber="";
      }
    })
  }

  redirectToCenters() {

  }
}
