import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {BloodRequest} from "../../../model/Responses/BloodRequest";
import {Center} from "../../../model/Center";
import {CenterService} from "../../../services/center.service";
import {BloodRequestService} from "../../../services/blood-request.service";
import {NgToastService} from "ng-angular-popup";
import {Router} from "@angular/router";
import {OfferDto} from "../../../model/OfferDto";

@Component({
  selector: 'app-make-offer-preview',
  templateUrl: './make-offer-preview.component.html',
  styleUrls: ['./make-offer-preview.component.css']
})
export class MakeOfferPreviewComponent implements OnInit {
  bloodReqest:BloodRequest = new BloodRequest();
  constructor( @Inject(MAT_DIALOG_DATA) public data: any, private centerService:CenterService,
               private bloodRequestServie:BloodRequestService,private alert: NgToastService,private readonly router:Router) { }
  centers:Center[] = []
  selectedCenter: Center = new Center();
  price= 0;
  ngOnInit(): void {
    this.centerService.getAllCenters().subscribe({
      next: res=>{
        this.centers = res
      }
    })
    this.bloodReqest = this.data.request
    console.log(this.bloodReqest)
  }
  changeCenter(center:Center){
    this.selectedCenter = center
  }

  makeOffer() {
    this.bloodReqest.price = this.price;
    this.bloodReqest.hospitalName = this.selectedCenter.name
    var bloodOffer = new OfferDto();
    bloodOffer.centerId = this.selectedCenter.id;
    bloodOffer.contactId = this.bloodReqest.id;
    console.log(bloodOffer)
    this.bloodRequestServie.createOffer(bloodOffer).subscribe({
      next:res=>{
        window.alert("You have sucssesfuly created an offer.")
        this.router.navigateByUrl('/blood-requests')
      },
      error: err => {
        window.alert(err.error.message)
        this.alert.error({detail: 'Error!',summary:err.error.message,duration:5000})
      }
    })
  }
}
