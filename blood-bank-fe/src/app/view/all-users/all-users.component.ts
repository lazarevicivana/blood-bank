import { Component, OnInit } from '@angular/core';
import {ApplicationUserService} from "../../services/applicationUser.service";
import {UserResponse} from "../../model/UserResponse";
import {ApplicationUser} from "../../model/ApplicationUser";

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
  users: UserResponse[] = [];
  constructor(private userService: ApplicationUserService) { }

  ngOnInit(): void {
    this.getAllUsers()
  }
  private getAllUsers(){
    this.userService.getAllUsers().subscribe(
      (response) => {
        this.users = response;
        console.log(this.users)
      }
    )
  }

}
