import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'mixr-sample-list',
  templateUrl: './sample-list.component.html',
  styleUrls: ['./sample-list.component.css']
})
export class SampleListComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  title: String = 'Hello Max';
  shouldDisplay = true;
  myInput = "";

  myList: Array<String> = ["robot", "family", "is", "so", "cool"];

  onClick() {
    this.myList.push(this.myInput);
    this.myInput = "";
  }

}
