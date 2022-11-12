import { Component, OnInit } from '@angular/core';
import {UserResponse} from "../../../model/UserResponse";
import {CustomerService} from "../../../services/customer.service";
import {DonorSearchRequest} from "../../../model/DonorSerachRequest";
import {Center} from "../../../model/Center";
import {UserToken} from "../../../model/UserToken";
import {TokenStorageService} from "../../../services/token-storage.service";
import {CenterAdministratorService} from "../../../services/center-administrator.service";

@Component({
  selector: 'app-center-donors',
  templateUrl: './center-donors.component.html',
  styleUrls: ['./center-donors.component.css']
})
export class CenterDonorsComponent implements OnInit {
  searchDonor:DonorSearchRequest;
  donors:UserResponse[]=[]
  public isLoaded:boolean = false;
  public center: Center;
  private userToken:UserToken;

  constructor(private customerService: CustomerService,private tokenService:TokenStorageService,private adminService: CenterAdministratorService) {
    this.searchDonor = new DonorSearchRequest();
    this.userToken = tokenService.getUser()
    this.center = new Center();
  }

  ngOnInit(): void {
    this.getCenter()
    this.searchDonor.searchSurname=""
    this.searchDonor.searchName=""
  }

  public search(){
    this.customerService.searchCenterDonors(this.searchDonor).subscribe(
      {
        next: result =>{
          this.donors = result;
        }
      }
    )
  }

  private getCenter(){
    this.adminService.getCenterForAdmin(this.userToken.id).subscribe(
      {
        next: res =>{
          this.center = res;
          this.isLoaded =true;
          this.searchDonor.centerId = this.center.id;
          this.getDonors();
        }
      }
    )
  }

  private getDonors()
  {
    if (this.center.id != null) {
      this.customerService.getCenterDonors(this.center.id).subscribe(
        {
          next: result => {
            this.donors = result;
          }
        }
      )
    }
  }
}
