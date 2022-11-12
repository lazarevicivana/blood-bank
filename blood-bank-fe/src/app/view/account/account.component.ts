import { Component, OnInit } from '@angular/core';
import {ApplicationUser} from "../../model/ApplicationUser";
import {ApplicationUserService} from "../../services/applicationUser.service";
import { ViewChild} from '@angular/core';
import {MatAccordion} from '@angular/material/expansion';
import {TokenStorageService} from "../../services/token-storage.service";
import {UserToken} from "../../model/UserToken";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion | undefined;
  country:string=""
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
    city: "",
    street: "",
    country: "",
    streetNumber: "",
    enabled: false,
    deleted: false,
    profession: {
      id: "",
      professionStatus: "",
      professionDescription: ""
    },
    gender: ""
  }
  userToken: UserToken;

  constructor(private userService: ApplicationUserService,private tkStorage:TokenStorageService,private toast:ToastrService) {
    this.userToken = this.tkStorage.getUser()
  }

  ngOnInit(): void {
    this.userService.getApplicationUserById(this.userToken.id).subscribe((user) =>
      (this.loggedCustomer = user , console.log(this.loggedCustomer)));

  }

  changeUsername() {
    if(this.username!=""){
      this.loggedCustomer.username=this.username
      this.username = ""
      this.accordion?.closeAll()
      this.userService.updateApplicationUser(this.loggedCustomer).subscribe()
      this.toast.success("You are successfully update profile!","Success")
    }

  }

  changePhone() {
    if(this.phone!="") {
      this.loggedCustomer.phone = this.phone
      this.phone = ""
      this.accordion?.closeAll()
      this.userService.updateApplicationUser(this.loggedCustomer).subscribe()
      this.toast.success("You are successfully update profile!","Success")
    }
  }

  changePassword() {
    if(this.password1 != ""){
      if(this.password1 === this.password2){
        this.loggedCustomer.password = this.password1
        this.password1 = ""
        this.password2 = ""
        this.accordion?.closeAll()
        this.userService.updateApplicationUser(this.loggedCustomer).subscribe()
        this.toast.success("You are successfully update profile!","Success")
      }
      else{
        this.toast.error("Passwords must match!","Error")
      }
    }
  }

  changeAddress() {
    if(this.street!="" && this.streetNumber!= "" && this.city!="" && this.country!="" ){
      this.loggedCustomer.country = this.country;
      this.loggedCustomer.street = this.street;
      this.loggedCustomer.streetNumber = this.streetNumber;
      this.loggedCustomer.city = this.city;
      this.country = ""
      this.city = ""
      this.street = ""
      this.streetNumber = ""
      this.accordion?.closeAll()
      this.userService.updateApplicationUser(this.loggedCustomer).subscribe()
      this.toast.success("You are successfully update profile!","Success")
    }
  }
}
