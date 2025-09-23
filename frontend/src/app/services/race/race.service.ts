import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RaceValue } from '../../models/race-value';

@Injectable({
  providedIn: 'root'
})
export class RaceService {

  private baseUrl = 'http://localhost:8080/api/race'; 

  constructor(private http: HttpClient) {}

  getAllRaces() : Observable<RaceValue[]> {
    return this.http.get<RaceValue[]>(this.baseUrl)
  }
}
