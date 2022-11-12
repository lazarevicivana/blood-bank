import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-blood-donor-question',
  templateUrl: './blood-donor-question.component.html',
  styleUrls: ['./blood-donor-question.component.css']
})
export class BloodDonorQuestionComponent implements OnInit {
  @Input() question : string ='';
  yes  = true;
  no = false;
  @Output() answerEmit : EventEmitter<boolean> = new EventEmitter<boolean>();
  answer  : boolean | undefined;
  constructor() { }

  ngOnInit(): void {
  }
  onRadioButton(answer: boolean){
    this.answerEmit.emit(answer);
  }

}
