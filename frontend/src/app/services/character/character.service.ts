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

  deleteCharacter(id : number) : Observable<CharacterValue> {
    return this.http.delete<CharacterValue>(this.baseUrl + "/" + id);
  }

  changeLevel(id : number, level : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/level/" + id + "/" + level, undefined)
  }

  changeSubclass(id : number, subclass : string) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/subclass/" + id + "/" + subclass, undefined)
  }

  changeCurrentHealth(id : number, currentHealth : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/currentHealth/" + id + "/" + currentHealth, undefined)
  }

  changeMaxHealth(id : number, maxHealth : number) : Observable<CharacterValue> {
    return this.http.put<CharacterValue>(this.baseUrl + "/maxHealth/" + id + "/" + maxHealth, undefined)
  }
}
