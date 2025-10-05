import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FeatValue } from '../../models/feat-value';

@Injectable({
  providedIn: 'root'
})
export class FeatService {

  private baseUrl = 'http://localhost:8080/api/feat'; 

  constructor(private http: HttpClient) {}

  getAllFeats() : Observable<FeatValue[]> {
    return this.http.get<FeatValue[]>(this.baseUrl)
  }
  
}
