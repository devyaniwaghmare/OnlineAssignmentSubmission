import { Subject } from "rxjs";
import { Subjects } from "./subjects";

export class Course {

     courseId:number;
     courseName:string;
     subjects:Subjects[];
     constructor(courseId:number,courseName:string,subjectList:Subjects[])
     {
         this.courseId=courseId;
         this.courseName=courseName;
         this.subjects=subjectList;

     }
}
