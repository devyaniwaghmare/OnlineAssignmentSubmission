import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/model/student.model';
import { AssignmentsService } from 'src/app/services/assignments.service';
import { AuthService } from 'src/app/services/auth.service';
import { CoursesService } from 'src/app/services/courses.service';
import { StudentService } from 'src/app/services/student.service';
@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  constructor(private router:Router,
    private _acRout:ActivatedRoute,
    private _courseSevice:CoursesService,
    private _studentService:StudentService,
    private _authService:AuthService,
    ) { }

  studenlList:Student[];
  ngOnInit(): void {

    let courseId = this._acRout.snapshot.params['id'];
     this._authService.getAllStudentsDeatilsByCourseId(courseId).subscribe(
      
      data => {
       
        this.studenlList = data;
      },
      err => {
        console.log(err)
      }
    )


    
  }
  
  updateStudents(studentId:number)
  {
    console.log(studentId);
    this.router.navigate(['/updateStudent',studentId])
  }

  deleteStudents(studId:number)
  {
    this._studentService.deleteUser(studId).subscribe(
      data => {

        this.ngOnInit();
      },
      err => {

        console.log("failed to delete");
      }
    )
  }
}
