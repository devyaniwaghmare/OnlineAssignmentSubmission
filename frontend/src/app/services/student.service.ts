import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../model/student.model';
import { Observable, Subject } from 'rxjs';
import { Login } from '../model/login.model';

@Injectable()
export class StudentService {

  private baseUrl = "http://localhost:8080";

  constructor(private http:HttpClient) { }

  getusers(): Observable<Student[]>{
    return this.http.get<Student[]>(this.baseUrl+"/students");
     
}

addUser(student:Student): Observable<any>
{
     
     return this.http.post<any>(this.baseUrl+"/students/register",student);
}

updateUser(sudentId:number,student:Student):Observable<Student[]>
{
   var subject=new Subject<Student[]>();
   this.http.put(this.baseUrl+"/students/"+sudentId,student).subscribe(r=>{
        console.log("updated");
        console.log(r);
        this.http.get<Student[]>(this.baseUrl+"/students").subscribe(result=>{
        subject.next(result);

        });
        

   });
   return subject.asObservable();

}

deleteUser(id:number):Observable<Student[]>{
  var subject=new Subject<Student[]>();
   this.http.delete(this.baseUrl+"/students/"+id).subscribe(r=>{
        console.log("deleted");
        console.log(r);
        this.http.get<Student[]>(this.baseUrl+"/students").subscribe(result=>{
        subject.next(result);

        });
        

   });
   return subject.asObservable();
}
getUserById(id:number):Observable<Student>{

  return this.http.get<Student>(this.baseUrl+"/students/"+id);
  
}  

/*checkCredential(loginCredential:Login):Observable<any>{

     return this.http.post<any>(this.baseUrl+"/token",loginCredential);
}


logginIn()
{
     return !!localStorage.getItem('tocken');
}*/
  
}
