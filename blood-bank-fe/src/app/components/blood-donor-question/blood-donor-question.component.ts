import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-blood-donor-question',
  templateUrl: './blood-donor-question.component.html',
  styleUrls: ['./blood-donor-question.component.css']
})
export class BloodDonorQuestionComponent implements OnInit {
  @Input() question : string ='';
  yes  :  string = 'yes';
  no : string = 'no';
  @Output() answerEmit : EventEmitter<string> = new EventEmitter<string>();
  answer  : string | undefined;
  constructor() { }

  ngOnInit(): void {
  }
  onRadioButton(answer: string){
    this.answerEmit.emit(answer);
  }

}
