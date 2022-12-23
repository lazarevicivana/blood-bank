import {Component, OnInit} from '@angular/core';
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {CenterAdministratorService} from "../../../services/center-administrator.service";
import {CenterAdministrator} from "../../../model/CenterAdministrator";
import {MatDialog} from "@angular/material/dialog";
import {AnotherAdminDialogComponent} from "./another-admin-dialog/another-admin-dialog.component";
import {MatStepper} from "@angular/material/stepper";
import {Router} from "@angular/router";
import {NgToastService} from "ng-angular-popup";


@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  public center: Center
  public admin: CenterAdministrator
  stepper: MatStepper | undefined;
  leter_reg = new RegExp("[a-zA-Z]+")
  email_reg = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
  jmbg_reg = new RegExp("[0-9]{13,13}")
  number_reg = new RegExp("[0-9]+")

  constructor(private centerService:CenterService, private centerAdminService:CenterAdministratorService,public dialog: MatDialog,private readonly router:Router,private alert: NgToastService) {
    this.center = new Center();
    this.admin = new CenterAdministrator()
    this.admin.firstLogIn = true
  }

  ngOnInit(): void {
  }

  createCenter(stepper:MatStepper) {
    if(this.validateCenterFields()){
      return
    }
    this.centerService.createCenter(this.center).subscribe({
      next: response => {
        this.center.id = response.id
        this.admin.center = this.center.id
        this.stepper = stepper
        this.stepper.next()
      },
      error: err => {
        this.alert.error({detail: 'Error!',summary:err.error.message,duration:5000})
      }
    })
  }

  createAdmin() {
    if(this.validateAdminFields())
      return
    this.centerAdminService.createAdmin(this.admin).subscribe({
      next: response => {
        this.resetAdminForm();
        this.openDialog();
      },
      error: err => {
        this.alert.error({detail: 'Error!',summary:err.error.message,duration:5000})
      }
    })
  }

  async redirectToCenters() : Promise<void>{
    await this.router.navigateByUrl('/facilities')
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

  private validateCenterFields():boolean{
    if(this.center.name == "" || this.center.name == null || this.center.name == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center name",duration:5000})
      return true
    }
    if(this.center.description == "" || this.center.description == null || this.center.description == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center descrtiption",duration:5000})
      return true
    }
    if(this.center.country == "" || this.center.country == null || this.center.country == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center country",duration:5000})
      return true
    }
    if(this.center.city == "" || this.center.city == null || this.center.city == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center city",duration:5000})
      return true
    }
    if(this.center.street === "" || this.center.street == null || this.center.street == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center street",duration:5000})
      return true
    }
    if(this.center.streetNumber === "" ||this.center.streetNumber == null || this.center.streetNumber == undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center street number",duration:5000})
      return true
    }
    if(this.center.longitude == null || this.center.longitude == undefined){
      this.alert.error({detail: 'Error!',summary:"Please select location on map",duration:5000})
      return true
    }

    if(this.center.avgGrade == null || this.center.avgGrade== undefined){
      this.alert.error({detail: 'Error!',summary:"Please enter center rating",duration:5000})
      return true
    }
    if(this.center.avgGrade > 5 || this.center.avgGrade<1){
      this.alert.error({detail: 'Error!',summary:"Please enter rating from 1 to 5",duration:5000})
      return true
    }
    console.log('provera'+this.leter_reg.test(this.center.country))
    if(!this.leter_reg.test(this.center.country) || !this.leter_reg.test(this.center.city)){
      this.alert.error({detail: 'Error!',summary:"Please enter only leters to name, country and city",duration:5000})
      return true
    }

    return false
  }

  private validateAdminFields():boolean{
    if(!this.leter_reg.test(<string>this.admin.country) || !this.leter_reg.test(<string>this.admin.city)
      ||!this.leter_reg.test(<string>this.admin.name) || !this.leter_reg.test(<string>this.admin.surname)){
      this.alert.error({detail: 'Error!',summary:"Please enter only leters to name, surname, country and city",duration:5000})
      return true
    }
    if(!this.jmbg_reg.test(<string>this.admin.jmbg)){
      this.alert.error({detail: 'Error!',summary:"JMBG have 13 numbers",duration:5000})
      return true
    }
    if(!this.email_reg.test(<string>this.admin.email)){
      this.alert.error({detail: 'Error!',summary:"Invalid email",duration:5000})
      return true
    }
    if(!this.number_reg.test(<string>this.admin.phone)){
      this.alert.error({detail: 'Error!',summary:"Invalid phone",duration:5000})
      return true
    }

    return false
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

  emptyAdminValidation():boolean {
    return (
      this.admin.name == "" || this.admin.name == undefined
      || this.admin.username == "" || this.admin.username == undefined
      || this.admin.surname == "" || this.admin.surname == undefined
      || this.admin.password == "" || this.admin.password == undefined
      || this.admin.email == "" || this.admin.email == undefined
      || this.admin.phone == "" || this.admin.phone == undefined
      || this.admin.country == "" || this.admin.country == undefined
      || this.admin.jmbg == "" || this.admin.jmbg == undefined
      || this.admin.street == "" || this.admin.street == undefined
      || this.admin.streetNumber == "" || this.admin.streetNumber == undefined
      || this.admin.city == "" || this.admin.city == undefined
      || this.admin.gender == "" || this.admin.gender == undefined
    )
  }
}
