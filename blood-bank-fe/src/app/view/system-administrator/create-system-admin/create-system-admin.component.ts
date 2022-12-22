import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { SystemAdministrator } from 'app/model/SystemAdministratorRequest';
import {NgToastService} from "ng-angular-popup";
import {CenterService} from "../../../services/center.service";
import {SystemAdministratorService} from "../../../services/system-administrator.service";

@Component({
  selector: 'app-create-system-admin',
  templateUrl: './create-system-admin.component.html',
  styleUrls: ['./create-system-admin.component.css']
})
export class CreateSystemAdminComponent implements OnInit {
  admin:SystemAdministrator
  leter_reg = new RegExp("[a-zA-Z]+")
  email_reg = new RegExp("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
  jmbg_reg = new RegExp("[0-9]{13,13}")
  number_reg = new RegExp("[0-9]+")

  constructor(private service:SystemAdministratorService,private readonly router:Router,private alert: NgToastService) {
    this.admin = new SystemAdministrator();
  }

  ngOnInit(): void {
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


  createAdmin() {
    if(this.validateAdminFields()){
      return
    }
    this.service.createAdmin(this.admin).subscribe({
      next: response => {
        this.resetAdminForm()
        this.alert.success({detail: 'Create System administrator successfully!', summary: "Success!", duration: 5000})
      },
      error: err => {
        this.alert.error({detail: 'Error!',summary:err.error.message,duration:5000})
      }
    })
  }

  async redirectToCenters() : Promise<void>{
    await this.router.navigateByUrl('/facilities')
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
