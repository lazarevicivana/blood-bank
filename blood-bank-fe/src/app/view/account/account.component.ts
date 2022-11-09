import { Component, OnInit } from '@angular/core';
import {ApplicationUser} from "../../model/ApplicationUser";
import {ApplicationUserService} from "../../services/applicationUser.service";
import { ViewChild} from '@angular/core';
import {MatAccordion} from '@angular/material/expansion';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion | undefined;
  city:string=""
  street:string=""
  streetNumber:string=""
  username:string=""
  phone:string=""
  password1:string =""
  password2:string =""
  loggedCustomer: ApplicationUser = {
    id: "",
    username: "",
    password: "",
    name: "",
    surname: "",
    phone: "",
    jmbg: "",
    email: "",
    userRole: "",
    address: {
      id: "",
      city: "",
      street: "",
      country: "",
      streetNumber: ""
    },
    enabled: false,
    deleted: false,
    profession: {
      id: "",
      professionStatus: "",
      professionDescription: ""
    },
    "gender": ""
  }

  constructor(private userService: ApplicationUserService) { }

  ngOnInit(): void {
    this.userService.getApplicationUserById().subscribe((user) =>
      (this.loggedCustomer = user));
  }

  changeUsername() {
    if(this.username!=""){
      this.loggedCustomer.username=this.username
      this.username = ""
      this.accordion?.closeAll()
      this.userService.updateApplicationUser(this.loggedCustomer).subscribe()
    }

  }

  changeEmail() {
    if(this.phone!="") {
      this.loggedCustomer.phone = this.phone
      this.phone = ""
      this.accordion?.closeAll()
    }
  }

  changePassword() {
    if(this.password1 != ""){
      if(this.password1 == this.password2){
        this.loggedCustomer.password = this.password1
        this.password1 = ""
        this.password2 = ""
        this.accordion?.closeAll()
      }
    }
  }

  changeMail() {
    if(this.street!="" && this.streetNumber!= "" && this.city!=""){
      this.loggedCustomer.address.street = this.street;
      this.loggedCustomer.address.streetNumber = this.streetNumber;
      this.loggedCustomer.address.city = this.city;
      this.city = ""
      this.street = ""
      this.streetNumber = ""
      this.accordion?.closeAll()
    }
  }
}
