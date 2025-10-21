import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CharacterValue } from '../../models/character-value';

@Injectable({
  providedIn: 'root'
})
export class PdfService {

  private baseUrl = 'http://localhost:8080/api/pdf'; 

  constructor(private http: HttpClient) {}

  downloadCharacterSheet(id : number) : Observable<Blob> {
    return this.http.get(this.baseUrl + "/character/" + id, { responseType: 'blob', });
  }
  
}
