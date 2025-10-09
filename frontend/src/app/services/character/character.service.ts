import { Injectable } from '@angular/core';
import { CharacterValue } from '../../models/character-value';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CharacterCreationValue } from '../../models/character-creation-value';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  
  private baseUrl = 'http://localhost:8080/api/character'; 

  constructor(private http: HttpClient) {}

  getAllCharacters() : Observable<CharacterValue[]> {
    return this.http.get<CharacterValue[]>(this.baseUrl);
  }

  getCharacterById(id : number) : Observable<CharacterValue> {
    return this.http.get<CharacterValue>(this.baseUrl + "/" + id);
  }

  createCharacter(creationValue : CharacterCreationValue) : Observable<CharacterValue> {
    return this.http.post<CharacterValue>(this.baseUrl, creationValue);
  }
}
