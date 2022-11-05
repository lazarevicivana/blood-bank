import { Component, OnInit } from '@angular/core';
import {navbarData} from "./nav-data";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
  navData =navbarData;


  constructor() { }

  onReschedule() {
    let list = document.querySelectorAll('.list');

  }

  ngOnInit(): void {
  }

  klikno(num: number) {
    let list = document.querySelectorAll('.list');
    let j = 0
    while(j<list.length){
      list[j++].className='list';
    }
    list[num].className='list active';

  }

  menuToggle() {
    let navigation = document.querySelector('.navigation');
    navigation!.classList.toggle('active')
  }
}
