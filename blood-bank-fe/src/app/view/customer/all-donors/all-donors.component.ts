import { Component, OnInit } from '@angular/core';
import {UserResponse} from "../../../model/UserResponse";
import {CustomerService} from "../../../services/customer.service";
import {DonorSearchRequest} from "../../../model/DonorSerachRequest";

@Component({
  selector: 'app-all-donors',
  templateUrl: './all-donors.component.html',
  styleUrls: ['./all-donors.component.css']
})
export class AllDonorsComponent implements OnInit {
  searchDonor:DonorSearchRequest;
  donors:UserResponse[]=[]
  constructor(private customerService: CustomerService) {
    this.searchDonor = new DonorSearchRequest();
  }

  ngOnInit(): void {
    this.customerService.getAllDonors().subscribe(
      {
        next: result =>{
          this.donors = result;
        }
      }
    )
    this.searchDonor.searchSurname=""
    this.searchDonor.searchName=""
  }

  public search(){
    this.customerService.searchAllDonors(this.searchDonor).subscribe(
      {
        next: result =>{
          this.donors = result;
        }
      }
    )
  }
}
