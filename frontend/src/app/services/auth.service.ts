import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Login } from '../model/login.model';
import { Router } from '@angular/router';
import { Student } from '../model/student.model';
import { Faculty } from '../model/faculty';
import { CoursesService } from './courses.service';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class AuthService {

  private baseUrl = "http://localhost:8080";

  constructor(private http:HttpClient,private _router:Router,private _courseSevice:CoursesService) { }

  checkCredential(loginCredential:Login):Observable<any>{

    return this.http.post<any>(this.baseUrl+"/token",loginCredential);
   }

/*addStudent(student:Student): Observable<any>
{
     
     return this.http.post<any>(this.baseUrl+"/students/register",student);
}*/

 addFaculty(faculty:Faculty): Observable<any>
  {
       
       return this.http.post<any>("http://localhost:8080/faculty/register",faculty)
   
  }

  getFacultyById(id:number):Observable<Faculty>{
 
    return this.http.get<Faculty>("http://localhost:8080/faculty/"+id);
    
  }  

  getAllStudentsDeatilsByCourseId(id:number):Observable<any>{

    return this.http.get<any>("http://localhost:8080/studcourses/"+id)
  }

logginIn()
{
    return !!sessionStorage.getItem('token');
}

getToken()
{
  return sessionStorage.getItem('token');
}


logoutUser()
{
  sessionStorage.removeItem('token'); 
  this._router.navigate(['/courses']);
  
}
}
