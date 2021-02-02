import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Faculty } from 'src/app/model/faculty';
import { AuthService } from 'src/app/services/auth.service';
import { FacultyService } from 'src/app/services/faculty.service';
@Component({
  selector: 'app-add-new-faculty',
  templateUrl: './add-new-faculty.component.html',
  styleUrls: ['./add-new-faculty.component.css']
})
export class AddNewFacultyComponent implements OnInit {

  faculty = new Faculty(null,null,null,null,null,null,null,null);
  constructor(private _facultyService:FacultyService
    ,private _authService:AuthService,
    private _router:Router) { }

  ngOnInit(): void {
  }

  addNewFaculty(){

    
    console.log(this.faculty.DOB.toString());
    let newFaculty = new Faculty(null,this.faculty.firstName,this.faculty.lastName,this.faculty.phone,this.faculty.gender,this.faculty.emailId,this.faculty.DOB,this.faculty.DOB.toString());
    this._authService.addFaculty(newFaculty).subscribe(
      data => {
             console.log(data);
             this._router.navigate(['/admin'])
             
      },
      err => {
            console.log(err);
      }
    )
  }
}
