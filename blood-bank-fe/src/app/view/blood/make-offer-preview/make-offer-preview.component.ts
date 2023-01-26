import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {BloodRequest} from "../../../model/Responses/BloodRequest";

@Component({
  selector: 'app-make-offer-preview',
  templateUrl: './make-offer-preview.component.html',
  styleUrls: ['./make-offer-preview.component.css']
})
export class MakeOfferPreviewComponent implements OnInit {
  bloodReqest:BloodRequest = new BloodRequest();
  constructor( @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.bloodReqest = this.data.request
    console.log(this.bloodReqest)
  }

}
