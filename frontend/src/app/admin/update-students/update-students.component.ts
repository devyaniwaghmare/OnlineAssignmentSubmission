import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/model/course.model';
import { Student } from 'src/app/model/student.model';
import { CoursesService } from 'src/app/services/courses.service';
import { StudentService } from 'src/app/services/student.service';
@Component({
  selector: 'app-update-students',
  templateUrl: './update-students.component.html',
  styleUrls: ['./update-students.component.css']
})
export class UpdateStudentsComponent implements OnInit {


  studentId:number;
  studentDetails=new Student(null,null,null,null,null,null,null,null);
 // enrolledCourse=new Course(null,null,null);
  courseList:Course[];
  courseName:string;
  constructor(private _acRouter:ActivatedRoute,
    private _route:Router,
    private _studentService:StudentService,
    private _courseService:CoursesService) { }


  ngOnInit(): void {

      this.studentId = this._acRouter.snapshot.params['id'];
      this._studentService.getUserById(this.studentId).subscribe(

        data => {
 
          this.studentDetails = data;
          console.log(this.studentDetails);
          this.courseName = data.courses.courseName;
        },
        err => {
           console.log(err);
        }
      )

      this._courseService.getCourseDetails().subscribe(
        data => {

          this.courseList = data;
        },
        err => {

          console.log(err)
        }
      )

  }

  updateStudent(studentId:number)
  {
  
     let selectedCourse:Course;
     console.log(this.courseName)
     for(let course of this.courseList)
     {
          if(this.courseName===course.courseName)
          {
            selectedCourse = course;
          }
     }

    let updatedStudents = new Student(null,
      this.studentDetails.firstName,
      this.studentDetails.lastName,
      this.studentDetails.phone,
      this.studentDetails.gender,
      this.studentDetails.emailId,
      this.studentDetails.password,
      selectedCourse);

      this._studentService.updateUser(this.studentId,updatedStudents).subscribe(
        data => {

          console.log(data);
          this._route.navigate(['/admin']);
        },
        err=>{

        }
      )
       
  }
}
