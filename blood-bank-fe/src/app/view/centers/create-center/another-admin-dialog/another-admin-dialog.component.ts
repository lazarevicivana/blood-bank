import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-another-admin-dialog',
  templateUrl: './another-admin-dialog.component.html',
  styleUrls: ['./another-admin-dialog.component.css']
})
export class AnotherAdminDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AnotherAdminDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {

  }

  onCancel(){
    this.dialogRef.close();
    this.data.stepper.next();
  }

  addAnother(){
    this.dialogRef.close();
  }
}
