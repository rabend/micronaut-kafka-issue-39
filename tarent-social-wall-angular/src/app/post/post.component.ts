import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  text: string;
  created_at: string;

  constructor() { }

  ngOnInit() {
    this.text = 'Sample text';
    this.created_at = '2018-09-21';
  }
}
