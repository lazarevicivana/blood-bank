import { Component, OnInit } from '@angular/core';
import {UserToken} from "../../model/UserToken";
import {TokenStorageService} from "../../services/token-storage.service";
import {ApplicationUserService} from "../../services/applicationUser.service";
import {ApplicationUser} from "../../model/ApplicationUser";
import {Router} from "@angular/router";
import {NgToastService} from "ng-angular-popup";

@Component({
  selector: 'app-first-login',
  templateUrl: './first-login.component.html',
  styleUrls: ['./first-login.component.css']
})
export class FirstLoginComponent implements OnInit {
  userToken: UserToken | undefined
  password1:string = ""
  password2:string = ""

  constructor(private tokenService: TokenStorageService,private userService: ApplicationUserService,private readonly router:Router,private alert: NgToastService) {
    this.userToken = tokenService.getUser()
  }

  ngOnInit(): void {
    // @ts-ignore
    this.userService.getApplicationUserById(this.userToken.user?.id!).subscribe((user) =>
      (this.loggedUser = user , console.log(this.loggedUser)
    ));
  }
  loggedUser: ApplicationUser = {
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
    gender: "",
    firstLogIn:true
  }

  changePassword() {
    if(this.validatePasswords())
    {
      console.log(this.validatePasswords(),'usloo')
      return
    }
    if(this.password1 == this.password2 && this.password1 !="" && this.password2 !="" )
    {
      this.loggedUser.firstLogIn = false
      this.loggedUser.password = this.password1
      this.userService.updateApplicationUser(this.loggedUser).subscribe(
        next =>{
          this.userToken = this.tokenService.getUser()
          this.userService.getApplicationUserById(this.userToken.user?.id!).subscribe((user) =>
            ( console.log('proooba',user,this.userToken)
            ));

          //window.location.reload();
          this.redirectToCenters()
        }
      )
    }
  }

  private validatePasswords():boolean{
    if(this.password1 != this.password2) {
      this.alert.error({detail: 'Error!', summary: "Passwords don't match", duration: 5000})
      return true
    }
    if(this.password1 == "" || this.password2 =="" || this.password1 == " " || this.password1 == " ")
    {
      this.alert.error({detail: 'Error!', summary: "Enter passwords", duration: 5000})
      return true
    }

    return false
  }

  async redirectToCenters() : Promise<void>{
    await this.router.navigateByUrl('/facilities')
  }
}
