import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../model/User";
import {HttpClient} from "@angular/common/http";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-customer-qr-list',
  templateUrl: './customer-qr-list.component.html',
  styleUrls: ['./customer-qr-list.component.css']
})
export class CustomerQrListComponent implements OnInit {
  codes:any=[]
  copy:any=[]
  userToken: User;
  sortOrder = 'asc';
  sort : string[] =['asc','dsc']
  filterStatus = 'all';
  status : string[] =['PENDING','ACCEPTED', 'REJECTED', 'all']

  constructor(private tkStorage:TokenStorageService,private http:HttpClient,private sanitizer: DomSanitizer) {
    this.userToken = this.tkStorage.getUser()
  }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/api/v1/customer/getCustomersQrCodes/'+this.userToken.id).subscribe(
      {
        next: r => {
          console.log('codes',r)
          this.codes = r;
          this.copy = r;
        }});
  }

  public getImage(base64:string){
    return this.sanitizer.bypassSecurityTrustUrl('data:image/jpg;base64,' + base64);
  }

  sortArray(sort: string) {
    this.sortOrder = sort
    if (this.sortOrder === 'asc') {
      // sort array in ascending order
      // @ts-ignore
      this.codes.sort((a, b) => {
        return new Date(a.date).getTime() - new Date(b.date).getTime();
      });
    } else {
      // sort array in descending order
      // @ts-ignore
      this.codes.sort((a, b) => {
        return new Date(b.date).getTime() - new Date(a.date).getTime();
      });
    }
  }

  filterArray(status: string) {
    this.filterStatus = status
    this.codes = this.copy
    console.log('cooopy:',this.copy)
    console.log('c:',this.codes)
    if (this.filterStatus == 'PENDING') {
      // @ts-ignore
      this.codes= this.codes.filter(item => item.status == 'PENDING');
    }else if (this.filterStatus == 'ACCEPTED') {
      // @ts-ignore
      this.codes= this.codes.filter(item => item.status == 'ACCEPTED');
    } if (this.filterStatus == 'REJECTED') {
      // @ts-ignore
       this.codes= this.codes.filter(item => item.status == 'REJECTED');
    }
    else {

      this.codes= this.codes
    }
    console.log(this.filterStatus)
  }

}
