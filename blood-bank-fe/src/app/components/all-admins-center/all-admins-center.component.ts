import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CenterAdministrator} from "../../model/CenterAdministrator";
import {CenterService} from "../../services/center.service";
import {FormControl, Validators} from "@angular/forms";
import {MedicalStaffResponse} from "../../model/Responses/MedicalStaffResponse";

@Component({
  selector: 'app-all-admins-center',
  templateUrl: './all-admins-center.component.html',
  styleUrls: ['./all-admins-center.component.css']
})
export class AllAdminsCenterComponent implements OnInit {
  @Output() onSelection: EventEmitter<string> = new EventEmitter()
  @Input() centerId: string = ''
  staffs:MedicalStaffResponse[]= []
  formControl =  new FormControl('',Validators.required);
  constructor(private centerService: CenterService) { }

  ngOnInit(): void {
    this.getAllAdmins()
  }
  private getAllAdmins(){
    this.centerService.getAllAdminsForCenter(this.centerId).subscribe(
      {
        next: res =>{
          this.staffs = res;
          console.log(this.staffs)
        }
      }
    )
  }

  selectedAdmins(value: string) {
    console.log(value)
    this.onSelection.emit(value)
  }
}
