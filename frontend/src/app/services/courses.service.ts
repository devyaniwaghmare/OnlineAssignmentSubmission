import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Course } from '../model/course.model';
@Injectable()
export class CoursesService {

  private _coursesUrl = "http://localhost:8080/courses";
  constructor(private http:HttpClient) { }

  getCourseDetails():Observable<Course[]>{
    return this.http.get<any>(this._coursesUrl);
  }

  getCourseDetailsById(id:any):Observable<Course>{
    return this.http.get<any>(this._coursesUrl+"/"+id);
  }

  removeCourseSubjectEntry(cid:any,sid:any):Observable<any>{
    return this.http.get<any>(this._coursesUrl+"/"+cid+"/"+sid);
  }

  updateCourse(id:number,updatedCourse:Course):Observable<any>{
    return this.http.put<any>(this._coursesUrl+"/"+id,updatedCourse);
  }
  
  addCourse(newCourse:Course):Observable<any>{
    return this.http.post<any>(this._coursesUrl,newCourse);
  }

  deleteCourse(courseId:number):Observable<any>{
    return this.http.delete<any>(this._coursesUrl+"/"+courseId);
  }

}
