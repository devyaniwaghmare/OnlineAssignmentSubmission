import { Injectable } from '@angular/core';
import { Observable,Subject } from 'rxjs';
import { Faculty } from '../model/faculty';
import { HttpClient} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  private baseurl="http://localhost:8080";
  constructor(private http:HttpClient) { }

  getFaculty(): Observable<Faculty[]>{
    return this.http.get<Faculty[]>(this.baseurl+"/faculty")
     
   }

  updateFaculty(fid:number,updatedFaculty:Faculty):Observable<any>
  {

    return this.http.put<any>(this.baseurl+"/faculty/"+fid,updatedFaculty)
  }
 
   deleteFaculty(id:number):Observable<Faculty[]>{
     var subject=new Subject<Faculty[]>();
      this.http.delete(this.baseurl+"/faculty/"+id).subscribe(r=>{
           console.log("deleted");
           console.log(r);
           this.http.get<Faculty[]>(this.baseurl+"/faculty").subscribe(result=>{
           subject.next(result);
 
           });
           
 
      });
      return subject.asObservable();
   }
   
   /*getFacultyById(id:number):Observable<Faculty>{
 
     return this.http.get<Faculty>(this.baseurl+"/faculty/"+id);
     
   } */ 

   
}
