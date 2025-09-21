import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BackgroundValue } from '../models/BackgroundValue';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackgroundService {

  private baseUrl = 'http://localhost:8080/api'; // replace with your backend

   constructor(private http: HttpClient) {}

   getAllBackgrounds() : Observable<BackgroundValue[]> {
    return this.http.get<BackgroundValue[]>(this.baseUrl + "/background")
   }
  
}
