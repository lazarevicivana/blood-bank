import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {DialogSignComponent} from "../dialog-sign/dialog-sign.component";

@Component({
  selector: 'app-sign-out',
  templateUrl: './sign-out.component.html',
  styleUrls: ['./sign-out.component.css']
})
export class SignOutComponent implements OnInit {

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
    this.openDialog('0ms','0ms')
  }
  openDialog(enterAnimationDuration: string, exitAnimationDuration: string): void {
    this.dialog.open(DialogSignComponent, {
      width: '500px',
      height:'200px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }

}

