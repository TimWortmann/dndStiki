import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ItemValue } from '../../models/item-value';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private baseUrl = 'http://localhost:8080/api/item'; 

  constructor(private http: HttpClient) {}

  getAllSpells() : Observable<ItemValue[]> {
    return this.http.get<ItemValue[]>(this.baseUrl)
  }
  
}
