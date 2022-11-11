import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {navbarData} from "./nav-data/nav-data";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../services/token-storage.service";
import {publicNavData} from "./nav-data/public-nav-data";
import {stuffNavData} from "./nav-data/nav-data-stuff";
import {adminSystemNavData} from "./nav-data/admin-system-nav-data";
import {customerNavData} from "./nav-data/customer-nav-data";

interface SideNavToggle{
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
  navData =navbarData;
  publicNavData = publicNavData;
  stuffNavData = stuffNavData;
  adminNavData = adminSystemNavData;
  customerNavData = customerNavData;
  @Input() userRole : string='';
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  screenWidth = 0;
  collapsed = false;
  @HostListener('window:resize',['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;

    this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});

  }

  constructor(private readonly router:Router,private tkStorage: TokenStorageService) {

  }


  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    const user = this.tkStorage.getUser();
      this.userRole = user.role;
      console.log(this.userRole);
  }

  clicked(num: number) {
    let list = document.querySelectorAll('.list');
    let j = 0
    while(j<list.length){
      list[j++].className='list';
    }
    list[num].className='list active';

  }

  menuToggle() {
    let navigation = document.querySelector('.navigation');

    if(this.collapsed == false){
      this.collapsed = true;
      this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
      navigation!.className='navigation active'
    }
    else{
      this.collapsed = false;
      this.onToggleSideNav.emit({collapsed: this.collapsed, screenWidth: this.screenWidth});
      navigation!.className='navigation'
    }
  }

  viewAccount() {
    console.log("acccc")
    this.router.navigateByUrl('/account')
    let list = document.querySelectorAll('.list');
    let j = 0
    while(j<list.length){
      list[j++].className='list';
    }
  }

  onSignOut() {
    this.tkStorage.signOut();
    window.location.reload();
    this.router.navigateByUrl("/");
  }
}
