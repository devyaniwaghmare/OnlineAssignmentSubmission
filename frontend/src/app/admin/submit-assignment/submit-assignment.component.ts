import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { Student } from 'src/app/model/student.model';
import { SubmitAssignment } from 'src/app/model/submit-assignment';
import { AuthService } from 'src/app/services/auth.service';
import { StudentService } from 'src/app/services/student.service';
import { UplaodAssignmentService } from 'src/app/services/uplaod-assignment.service';
import { StudentDetailsComponent } from '../student-details/student-details.component';

@Component({
  selector: 'app-submit-assignment',
  templateUrl: './submit-assignment.component.html',
  styleUrls: ['./submit-assignment.component.css']
})
export class SubmitAssignmentComponent implements OnInit {

  constructor(private _router:Router,
    private _acRouter:ActivatedRoute,
    private _studentService:StudentService,
    private _authService:AuthService,
    private _uploadAssignment:UplaodAssignmentService) { }

  studentId:number;
  assignmentId:number;
  studentDetails:Student;
  studentEmailId:string;
  ngOnInit(): void {
 
    this.studentId = this._acRouter.snapshot.params['sid'];
    this.assignmentId = this._acRouter.snapshot.params['aid'];
    console.log(this.assignmentId+""+this.studentId)

    this._studentService.getUserById(this.studentId).subscribe(
      data => {
        console.log(data)
        this.studentDetails = data;
        this.studentEmailId = data.emailId;
      }
    )


  }

 
  selectedFile: File = null;
  comments:string;

  onFileChanged(event)
  {
    console.log(event);
    this.selectedFile = event.target.files[0]
  }

  submitAssignment()
  {

    let submission = new SubmitAssignment(null,this.studentDetails.emailId.toString(),null,null,null,null,this.assignmentId);
    console.log(submission)
    this._uploadAssignment.submitAssignment(this.selectedFile,submission).subscribe(
      data => {
        console.log(data);
        this._router.navigate(['student',this.studentId]);
      },
      err => {
          console.log(err)
      }
    )

  }
}
