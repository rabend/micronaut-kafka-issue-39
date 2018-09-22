import {Component, OnInit} from '@angular/core';
import {BackendClientService} from '../backend-client.service';
import {interval} from 'rxjs';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  text: string;
  created: string;
  source: string;
  imageURLs: string[];
  start: string;
  end: string;
  location: string;

  constructor(private backendClient: BackendClientService) {
  }

  ngOnInit() {
    this.backendClient.getNewPost().subscribe(data => {
      this.text = data['text'];
      this.created = data['created'];
      this.source = data['source'];
      this.imageURLs = data['mediaURLs'];
      this.start = data['start'];
      this.end = data['end'];
      this.location = data['location'];
    });

    this.refreshPost();
  }

  refreshPost() {
    interval(1000 * 10).subscribe(x => {
      this.backendClient.getNewPost().subscribe(data => {
        this.text = data['text'];
        this.created = data['created'];
        this.source = data['source'];
        this.imageURLs = data['mediaURLs'];
        this.start = data['start'];
        this.end = data['end'];
        this.location = data['location'];
      });
    });
  }
}
