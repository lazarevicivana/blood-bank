import {Component, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  @Input() collapsed =true;
  @Input() screenWidth = 0;
  constructor() { }

  ngOnInit(): void {
    console.log(this.collapsed);
    console.log(this.screenWidth);
  }
  getBodyClass(): string{
    let styleClass = '';
    if(this.collapsed && this.screenWidth > 2133){
      styleClass = 'body-trimmed;'
    }
    else if(this.collapsed && this.screenWidth <= 2133 && this.screenWidth > 0){
      styleClass = 'body-md-screen';
    }
    return styleClass;
  }

}
