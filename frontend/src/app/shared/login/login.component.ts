import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/model/login.model';
import { StudentService } from 'src/app/services/student.service';
import { Router } from '@angular/router';
import { FacultyService } from 'src/app/services/faculty.service';
import { AuthService } from 'src/app/services/auth.service';
import { CoursesService } from 'src/app/services/courses.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private _authService:AuthService,private _facultyService:FacultyService,private router:Router,private _courseService:CoursesService) { }

  
  loginCredential = new Login("", "");

  ngOnInit(): void {


  }

  loginUser()
  {

      console.log(this.loginCredential);
      this._authService.checkCredential(this.loginCredential).subscribe(
      res => {

        console.log(res.token);
       
        if(res.role=="STUDENT")
        {
          //localStorage.setItem('token',res.token);
          sessionStorage.setItem('token',res.token);
          this.router.navigate(['/student',res.userId]);
        }
        if(res.role=="FACULTY")
        {

         // localStorage.setItem('token',res.token);
          sessionStorage.setItem('token',res.token);
          this._authService.getFacultyById(res.userId).subscribe(
            data => {
              console.log(data)
              this.router.navigate(['/faculty',res.userId]);
            },
            err => {
              console.log(err)
              this.router.navigate(['/login'])
            }
          )
      
        }
        if(res.role=="ADMIN")
        {
          sessionStorage.setItem('token',res.token);
          this._authService.getFacultyById(res.userId).subscribe(
            data => {
              console.log(data)
              this.router.navigate(['/admin',res.userId]);
            },
            err => {
              console.log(err)
              this.router.navigate(['/login'])
            }
          )
        }
     

      },
      err => console.log(err)
    )
       
  }
  
}
