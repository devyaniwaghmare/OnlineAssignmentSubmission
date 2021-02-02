import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course.model';
import { Student } from 'src/app/model/student.model';
import { User } from 'src/app/model/user.model';
import { StudentService } from 'src/app/services/student.service';
import { Router } from '@angular/router';
import { CoursesService } from 'src/app/services/courses.service';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Faculty } from 'src/app/model/faculty';
import { FacultyService } from 'src/app/services/faculty.service';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private _studentService:StudentService,private router: Router,private _courseService:CoursesService,
    private _authService:AuthService) { }

    userModel = new User(null,"","","","","","","");
    types = ["FACULTY","STUDENT","OTHER"];
    role:string;
    courseHasError:boolean;
    typeHasError:boolean;
    show:boolean=true; 
    cources = []
  ngOnInit(): void {

  this._courseService.getCourseDetails().subscribe(
    data =>{ this.cources = data
      console.log(this.cources);
      this.cources = data;
    },
    err => console.log(err)
 
    );

}



  validateCourse(course:any)
    {
        if(course=="default")
        {
            this.courseHasError=true;
        }
        else
        {
            this.courseHasError=false;
        }
    }
    validateType(type:any)
    {
        if(type=="default")
        {
          this.typeHasError=true;
        }
        else
        {

            if(this.role=="STUDENT")
            {
              
                this.typeHasError=false;
                
            }
            else{
              
                this.typeHasError=false;
                this.show=false;
            }
            
           
        }
    }

    onSubmit()
    {

      
     if(this.role=="STUDENT")
     {


      let selectedCourse:Course;
      for(let course of this.cources)
      {
           if(this.userModel.course===course.courseName)
           {
             selectedCourse = course;
           }
      }
     
      let registerStudent = new Student(null,this.userModel.firstName,this.userModel.lastName,this.userModel.phone,this.userModel.gender,this.userModel.emailId,this.userModel.password,selectedCourse);
    
      this._studentService.addUser(registerStudent)
      .subscribe(
        res => {
          console.log(res);
          localStorage.setItem('token',res.token)
          sessionStorage.setItem('token',res.token)
         // this.router.navigate(['/student']);
         this.router.navigate(['/student',res.userId]);
          
        },
        err => {
          console.log(err);
         this.router.navigate(['/register']);
         
        }
      )
     }
     if(this.role=="FACULTY")
     {

         let registerFaculty = new Faculty(null,this.userModel.firstName,this.userModel.lastName,this.userModel.phone,this.userModel.gender,this.userModel.emailId,null,this.userModel.password);
         console.log(registerFaculty);
         this._authService.addFaculty(registerFaculty).subscribe(
          data => {
            console.log(data)
            sessionStorage.setItem('token',data.token)
            this.router.navigate(['/faculty'],data.userId)
          },
          err => {
            console.log(err)
            this.router.navigate(['/register']);
          }
         )
  
    }
    

    }

}
