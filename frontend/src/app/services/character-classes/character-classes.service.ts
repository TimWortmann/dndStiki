import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BackgroundValue } from '../../models/background-value';
import { CharacterClassValue } from '../../models/character-class-value';

@Injectable({
  providedIn: 'root'
})
export class CharacterClassesService {

  private baseUrl = 'http://localhost:8080/api/class'; 

  constructor(private http: HttpClient) {}

  getAllCharacterClasses() : Observable<CharacterClassValue[]> {
    return this.http.get<CharacterClassValue[]>(this.baseUrl)
  }
  
}
