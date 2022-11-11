import {Component, OnInit} from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {CenterAdministratorService} from "../../../services/center-administrator.service";
import {CenterAdministrator} from "../../../model/CenterAdministrator";
import {MatDialog} from "@angular/material/dialog";
import {AnotherAdminDialogComponent} from "./another-admin-dialog/another-admin-dialog.component";
import {MatStepper} from "@angular/material/stepper";


@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  public center: Center
  public admin: CenterAdministrator
  stepper: MatStepper | undefined;

  constructor(private centerService:CenterService, private centerAdminService:CenterAdministratorService,public dialog: MatDialog) {
    this.center = new Center();
    this.admin = new CenterAdministrator()
  }

  ngOnInit(): void {
  }

  createCenter(stepper:MatStepper) {
   this.centerService.createCenter(this.center).subscribe({
      next: response => {
        this.center.id = response.id
        this.admin.center = this.center.id
        this.stepper = stepper
      }
    })
  }

  createAdmin() {
    if(this.admin.username != "") {
      this.centerAdminService.createAdmin(this.admin).subscribe({
        next: response => {
          this.resetAdminForm();
          this.openDialog();
        }
      })
    }else{
      this.openDialog();
    }
  }

  redirectToCenters() {

  }

  openDialog(): void {
    let dialogRef = this.dialog.open(AnotherAdminDialogComponent, {
      width: '380px',
      height:'220px',
      data: { name: this.center.name,stepper: this.stepper}
    });

    dialogRef.afterClosed().subscribe(result => {
        console.log(result);
        if (result == false){
          this.stepper?.next();
          console.log('ss'+result);
        }
        else
          this.resetAdminForm();

    });
  }

  private resetAdminForm(){
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
}
