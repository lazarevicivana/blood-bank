import { Component, OnInit } from '@angular/core';
import {BloodRequest} from "../../../model/Responses/BloodRequest";
import {BloodType} from "../../../model/BloodType";
import {MatDialog} from "@angular/material/dialog";
import{MakeOfferPreviewComponent} from "../make-offer-preview/make-offer-preview.component";
import {BloodRequestService} from "../../../services/blood-request.service";

@Component({
  selector: 'app-blood-requests',
  templateUrl: './blood-requests.component.html',
  styleUrls: ['./blood-requests.component.css']
})
export class BloodRequestsComponent implements OnInit {

  constructor(private dialog : MatDialog, private  bloodRequestService:BloodRequestService) { }
  requests:BloodRequest[] = []
  ngOnInit(): void {
    this.bloodRequestService.getContracts().subscribe({
      next: res=>{
        this.requests = res;
        console.log(res)
      }
    })

  }

  makeOffer(br: BloodRequest) {
    console.log(br)
    this.dialog.open(MakeOfferPreviewComponent, {
      width: '50%',
      height:'80%',
      data: { request: br}
    });
  }
}
