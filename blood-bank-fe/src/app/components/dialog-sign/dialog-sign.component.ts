import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {TokenStorageService} from "../../services/token-storage.service";

@Component({
  selector: 'app-dialog-sign',
  templateUrl: './dialog-sign.component.html',
  styleUrls: ['./dialog-sign.component.css']
})
export class DialogSignComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogSignComponent>,private router:Router,private tokenStorageService:TokenStorageService) { }

  ngOnInit(): void {
  }

  signOut() {
    this.tokenStorageService.signOut()
    this.router.navigate(['']).then(()=>{
      window.location.reload();
    })
  }
  cancelSignOut() {
    // if(this.tokenStorageService.getUser().role ==='Doctor')
    //   this.router.navigate(['dashboard'])
    // if(this.tokenStorageService.getUser().role ==='Manager')
    //   this.router.navigate(['rooms'])
  }
}
