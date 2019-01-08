import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SocialPost} from './social-post';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendClientService {

  private backendUrl = 'http://localhost:5000/socialposts';

  constructor(private httpClient: HttpClient) { }

  getNewPost(): Observable<SocialPost> {
    return this.httpClient.get<SocialPost>(this.backendUrl)
      .pipe(
        retry(3)
      );
  }
}
