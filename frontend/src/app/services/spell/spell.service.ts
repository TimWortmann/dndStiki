import { Injectable } from '@angular/core';
import { SpellValue } from '../../models/spell-value';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpellService {

  private baseUrl = 'http://localhost:8080/api/spell'; 

  constructor(private http: HttpClient) {}

  getAllSpells() : Observable<SpellValue[]> {
    return this.http.get<SpellValue[]>(this.baseUrl)
  }
  
}
