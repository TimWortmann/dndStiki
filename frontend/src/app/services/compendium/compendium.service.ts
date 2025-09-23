import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompendiumService {

  private baseUrl = 'http://localhost:8080/api/compendium';

  constructor(private http: HttpClient) {}

  getCompendiumFileName(): Observable<string> {
    return this.http.get<string>(this.baseUrl, { responseType: 'text' as 'json' });
  }

  uploadCompendium(file: File): Observable<string> {
    
    const formData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post<string>(this.baseUrl, formData, { responseType: 'text' as 'json' });
  }

  deleteCompendium() : Observable<string> {
    return this.http.delete<string>(this.baseUrl, { responseType: 'text' as 'json' });
  }
  
}
