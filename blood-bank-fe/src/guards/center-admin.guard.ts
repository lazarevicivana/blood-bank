import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../app/services/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CenterAdminGuard implements CanActivate {
  constructor(private toast: ToastrService,private tokenStorageService:TokenStorageService,private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.tokenStorageService.isLoggedIn() && this.tokenStorageService.getUser().role === "ROLE_CENTER_ADMIN"){
      return true
    }
    this.router.navigate(['']).then(()=>{
      this.toast.error("Please authorize","Error")
    });
    return false;
  }

}
