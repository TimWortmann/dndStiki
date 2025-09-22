import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompendiumService {

  private baseUrl = 'http://localhost:8080/api'; // replace with your backend

  constructor(private http: HttpClient) {}

  uploadCompendium(compendiumContent: string): Observable<string> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml'
    });

    return this.http.post<string>(this.baseUrl + "/compendium", compendiumContent, { headers, responseType: 'text' as 'json' });
  }
  
}
