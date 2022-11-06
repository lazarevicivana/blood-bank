import { Component, OnInit } from '@angular/core';
import {Customer} from "../../model/Customer";
import {CustomerService} from "../../services/customer.service";
import { ViewChild} from '@angular/core';
import {MatAccordion} from '@angular/material/expansion';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion | undefined;
  username:string=""
  phone:string=""
  password1:string =""
  password2:string =""
  loggedCustomer: Customer = {
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

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe((customer) =>
      (this.loggedCustomer = customer));
  }

  changeUsername() {
    if(this.username!=""){
      this.loggedCustomer.username=this.username
      this.username = ""
      this.accordion?.closeAll()
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
}
