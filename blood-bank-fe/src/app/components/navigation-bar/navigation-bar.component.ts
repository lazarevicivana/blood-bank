import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {navbarData} from "./nav-data/nav-data";
import {NavigationEnd, Router} from "@angular/router";
import {TokenStorageService} from "../../services/token-storage.service";
import {publicNavData} from "./nav-data/public-nav-data";
import {stuffNavData} from "./nav-data/nav-data-stuff";
import {adminSystemNavData} from "./nav-data/admin-system-nav-data";
import {customerNavData} from "./nav-data/customer-nav-data";
import {filter} from "rxjs";
import {UserToken} from "../../model/UserToken";
import {ApplicationUser, ApplicationUserImp} from "../../model/ApplicationUser";
import {ApplicationUserService} from "../../services/applicationUser.service";

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
  logedIn:boolean=false
  navData =navbarData;
  publicNavData = publicNavData;
  stuffNavData = stuffNavData;
  adminNavData = adminSystemNavData;
  loggedUser: UserToken | undefined
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


  constructor(private readonly router:Router,private tkStorage: TokenStorageService,private userService: ApplicationUserService) {
    // @ts-ignore
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      this.loggedUser=(this.tkStorage.getUser())

      this.loggedUser= this.tkStorage.getUser()
      // @ts-ignore
      this.userService.getApplicationUserById(this.loggedUser.user?.id).subscribe((u) => (this.user.firstLogIn = u.firstLogIn));
      console.log('uuseer',this.user)

      if(this.loggedUser.user?.id==""){
        this.logedIn = false

      }
      else {
        this.logedIn= true
      }
    });
  }
  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    const user = this.tkStorage.getUser();
    console.log(user.user?.userRole);
      this.userRole = user.user?.userRole!;
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
    this.router.navigateByUrl("").then(value => {
      window.location.reload();
      }
    )
  }

  firstLogIn()
  {
    return this.user.firstLogIn && this.loggedUser?.user?.userRole=="ROLE_SYSTEM_ADMIN"
  }

  user: ApplicationUser = {
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

}
