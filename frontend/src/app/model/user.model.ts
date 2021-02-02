export class User {

    studId:number;
     firstName:string;
     lastName:string;
     phone:string;
     gender:string;
     emailId:string;
     password:string;
     course:string;
    
   
     constructor(studId:number,firstName:string,lastName:string,phone:string,gender:string,email:string,password:string,course:string)
     {
     
         this.studId = studId;
         this.firstName = firstName;
         this.lastName = lastName;
         this.phone = phone;
         this.gender = gender;
         this.course = course;
         this.emailId = email;
         this.password = password;
         
       
     }
}
