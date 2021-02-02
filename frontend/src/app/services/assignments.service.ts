import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Assignment } from '../model/assignment';
@Injectable({
  providedIn: 'root'
})
export class AssignmentsService {


  private _assignmentURL = "http://localhost:8080/assignment";
  constructor(private http:HttpClient) { }

 getAssignmentDeatilsByCourseId(courseId:number):Observable<any>
 {
   return this.http.get<any>(this._assignmentURL);
 }

 getSubmittedAssignmentDetailsById(assignmentId:number):Observable<any>{

    return this.http.get<any>("http://localhost:8080/submittedAssignment//welcome"+"/"+assignmentId);
 }

}
