import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BackgroundValue } from '../models/BackgroundValue';

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
