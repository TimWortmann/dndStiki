import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BackgroundValue } from '../../models/background-value';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackgroundService {

  private baseUrl = 'http://localhost:8080/api/background'; 

  constructor(private http: HttpClient) {}

  getAllBackgrounds() : Observable<BackgroundValue[]> {
    return this.http.get<BackgroundValue[]>(this.baseUrl)
  }

  getBackground(name : string) : Observable<BackgroundValue> {
      return this.http.get<BackgroundValue>(this.baseUrl + "/" + encodeURIComponent(name))
  }
  
}
