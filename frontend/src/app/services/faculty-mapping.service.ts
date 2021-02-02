import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { FacultyMapping } from '../model/faculty-mapping';
@Injectable({
  providedIn: 'root'
})
export class FacultyMappingService {

  private _mappingUrl = "http://localhost:8080/mapping";
  constructor(private http:HttpClient) { }

  getMappingDetails():Observable<any>{

    return this.http.get<any>(this._mappingUrl);
  }

  addFacultyMapping(facultyMap:FacultyMapping):Observable<FacultyMapping>
  {
    return this.http.post<FacultyMapping>(this._mappingUrl,facultyMap);
  }

  getFacultyMappingById(mappingId:number):Observable<FacultyMapping>
  {
    return this.http.get<FacultyMapping>(this._mappingUrl+"/"+mappingId)
  }
  deleteFacultyMappingById(mappingId:number):Observable<any>
  {
    return this.http.delete<any>(this._mappingUrl+"/"+mappingId);
  }
}
