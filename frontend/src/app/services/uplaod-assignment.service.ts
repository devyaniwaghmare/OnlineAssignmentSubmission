import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AssignmentComponent } from '../assignment/assignment.component';
import { Assignment } from '../model/assignment';
import { SubmitAssignment } from '../model/submit-assignment';
@Injectable({
  providedIn: 'root'
})
export class UplaodAssignmentService {

  

  constructor(private http: HttpClient) { }

  uploadFile(selectedFile: File,assignment:Assignment) {

    let baseURL = "http://localhost:8080/"
    const uploadData = new FormData();
    uploadData.append("file", selectedFile);
    uploadData.append("assignment",JSON.stringify(assignment));
    console.log(uploadData);
    return this.http.post(baseURL.concat("single/upload"), uploadData, { responseType: 'text' });
  }

  submitAssignment(selectedFile:File,assignment:SubmitAssignment){

    let  baseUrl = "http://localhost:8080/"
    const uploadData = new FormData();
    uploadData.append("file", selectedFile);
    uploadData.append("assignment",JSON.stringify(assignment));
    console.log(uploadData);
    return this.http.post(baseUrl.concat("submit/upload"), uploadData, { responseType: 'text' });
  }
}
