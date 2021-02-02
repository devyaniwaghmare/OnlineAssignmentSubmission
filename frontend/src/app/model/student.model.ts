import { Course } from "./course.model";

export class Student {

     studId:number;
     firstName:string;
     lastName:string;
     phone:string;
     gender:string;
     emailId:string;
     password:string;
     courses:Course;
   
     constructor(studId:number,firstName:string,lastName:string,phone:string,gender:string,email:string,password:string,course:Course)
     {
     
         this.studId = studId;
         this.firstName = firstName;
         this.lastName = lastName;
         this.phone = phone;
         this.gender = gender;
         this.emailId = email;
         this.password = password;
         this.courses= course;
       
     }
    
}
