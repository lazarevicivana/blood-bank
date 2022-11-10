import { Component } from '@angular/core';
import {TokenStorageService} from "./services/token-storage.service";

interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blood-bank-fe';
  isLoggedIn = false;
  isSideNavCollapsed = false;
  screenWidth = 0;

  username?: string;
  constructor(private tokenStorageService: TokenStorageService) { }
  onToggleSideNav(data: SideNavToggle):void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.tokenStorageService.getToken());
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      console.log(user)
      this.username = user.username;
    }
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;

  }
}
