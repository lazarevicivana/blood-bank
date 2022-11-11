import {Component, Input, OnInit} from '@angular/core';
import {UserResponse} from "../../model/UserResponse";

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {
  @Input() user =  new UserResponse();
  constructor() { }

  ngOnInit(): void {
  }
  public resolveUserRole(){
    if(this.user.role === 'ROLE_CENTER_ADMIN'){
        return "Medical stuff"
    }
    if(this.user.role === 'ROLE_SYSTEM_ADMIN'){
        return "Admin"
    }
    if(this.user.role === 'ROLE_CUSTOMER'){
        return "Patient"
    }
    return "User"
  }
}
