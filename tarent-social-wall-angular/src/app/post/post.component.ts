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
  imageSource: string;

  constructor(private backendClient: BackendClientService) {
  }

  ngOnInit() {
    this.backendClient.getNewPost().subscribe(data => {
      this.text = data['text'];
      this.created = data['created'];
    });

    this.refreshPost();
  }

  refreshPost() {
    interval(1000 * 10).subscribe(x => {
      this.backendClient.getNewPost().subscribe(data => {
        this.text = data['text'];
        this.created = data['created'];
      });
    });
  }
}
