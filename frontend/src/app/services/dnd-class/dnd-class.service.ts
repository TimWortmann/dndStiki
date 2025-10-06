import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DndClassValue } from '../../models/dnd-class-value';

@Injectable({
  providedIn: 'root'
})
export class DndClassService {

  private baseUrl = 'http://localhost:8080/api/class'; 

  constructor(private http: HttpClient) {}

  getAllDndClasses() : Observable<DndClassValue[]> {
    return this.http.get<DndClassValue[]>(this.baseUrl)
  }
  
}
