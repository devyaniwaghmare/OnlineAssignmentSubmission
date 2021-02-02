import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Faculty } from 'src/app/model/faculty';
import { AuthService } from 'src/app/services/auth.service';
import { FacultyService } from 'src/app/services/faculty.service';
@Component({
  selector: 'app-update-faculty',
  templateUrl: './update-faculty.component.html',
  styleUrls: ['./update-faculty.component.css']
})
export class UpdateFacultyComponent implements OnInit {

  
  facultyId:number;
  firstName:string;
  lastName:string;
  emailId:string;
  phone:string;
  DOB:Date;
  gender:string;
  password:string;
  constructor(private _authService:AuthService,
    private _acRouter:ActivatedRoute,
    private _route:Router,
    private _facultyService:FacultyService) { }

  ngOnInit(): void {

    this.facultyId = this._acRouter.snapshot.params['id'];
   this._authService.getFacultyById(this.facultyId).subscribe(
     data => {
      
      this.facultyId = data.fid;
      this.firstName = data.firstName;
      this.lastName = data.lastName;
      this.phone = data.phone;
      this.gender = data.gender;
      this.emailId = data.emailId;
      this.DOB = data.DOB;
      this.password = data.password;

     },
     err => {
        console.log(err)
     }
   )

  }

  updateFaculty(){
     
    let updatedFaculty = new Faculty(null,this.firstName,this.lastName,this.phone,this.gender, this.emailId,this.DOB,this.password);
    console.log(updatedFaculty);
    this._facultyService.updateFaculty(this.facultyId,updatedFaculty).subscribe(
      data => {
        console.log(data);
        this._route.navigate(['/admin'])
      },
      err=>{
        console.log(err)
      }
    )
    
  }

}
