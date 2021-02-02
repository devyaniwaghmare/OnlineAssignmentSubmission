import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Subjects } from '../model/subjects';

@Injectable({
  providedIn: 'root'
})

export class SubjectsService {

  
private baseUrl = "http://localhost:8080";
  constructor(private http:HttpClient) { }

getSubjects():Observable<Subjects[]>
{
  return this.http.get<Subjects[]>(this.baseUrl+"/subjects");
}

getSubjectById(subdId:number):Observable<Subjects>
{
  return this.http.get<Subjects>(this.baseUrl+"/subjects"+"/"+subdId);
}

deleteSubjectById(subId:number):Observable<any>
{
  return this.http.delete<any>(this.baseUrl+"/subjects"+"/"+subId);
}

addNewSubject(newSubject:Subjects):Observable<any>
{
  return this.http.post<any>(this.baseUrl+"/subjects",newSubject);
}
}
