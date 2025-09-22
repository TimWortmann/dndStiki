import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompendiumService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getCompendiumFileName(): Observable<string> {
    return this.http.get<string>(this.baseUrl + "/compendium", { responseType: 'text' as 'json' });
  }

  uploadCompendium(file: File): Observable<string> {
    
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post<string>(this.baseUrl + "/compendium", formData, { responseType: 'text' as 'json' });
  }
  
}
