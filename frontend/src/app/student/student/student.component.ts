import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { Router,ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/model/student.model';
import { FacultyMappingService } from 'src/app/services/faculty-mapping.service';
import { Assignment } from 'src/app/model/assignment';
import { AssignmentsService } from 'src/app/services/assignments.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students = [];
  studentId:number;
  studentDetails:Student;
  assignments:Assignment;
  courseId:number;
  firstName:string;
  lastName:string;
  assignmentId:number;
  constructor(private _studentService:StudentService,
    private _acRouter:ActivatedRoute,
    private _facultymapping:FacultyMappingService,
    private _assignmentService:AssignmentsService,
    private _router:Router) { }

  ngOnInit(): void {

     this.studentId =  this._acRouter.snapshot.params['id'];
     this._studentService.getUserById(this.studentId).subscribe(
       data => {
        
       // this.studentDetails = data;
        this.firstName = data.firstName;
        console.log(this.studentDetails)
         this._assignmentService.getAssignmentDeatilsByCourseId(data.courses.courseId).subscribe(
          data => {
            console.log(data)
             this.assignments = data;

     
          }
        )

       }

     )
     
     
    
  }

  addAssignment(assignmentId:number)
  {

    console.log(assignmentId, this.studentId);
    this._router.navigate(['/submitAssignment',this.studentId,assignmentId]);
  }
}
