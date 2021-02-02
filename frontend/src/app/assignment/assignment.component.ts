import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';
import { Assignment } from '../model/assignment';
import { Faculty } from '../model/faculty';
import { AuthService } from '../services/auth.service';
import { UplaodAssignmentService } from '../services/uplaod-assignment.service';
import { Subjects } from 'src/app/model/subjects';
import { Course } from 'src/app/model/course.model';
import { FacultyMapping } from '../model/faculty-mapping';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment.component.html',
  styleUrls: ['./assignment.component.css']
})
export class AssignmentComponent implements OnInit {

  constructor(private _router:Router,
    private _acRouter:ActivatedRoute,
    private _authService:AuthService,
    private _uploadAssignment:UplaodAssignmentService) { }

 
  facultyMappingId:number;
  instruction:string;
  dueDate :Date;
  score:number;
  facultyId:number;
  faculty=new Faculty(null,null,null,null,null,null,null,null);
  selectedFile: File = null;
  ngOnInit(): void {

    this.facultyId = this._acRouter.snapshot.params['fid'];
    //this.courseId = this._acRouter.snapshot.params['cid'];
    //this.subjectId = this._acRouter.snapshot.params['sid'];


    this.facultyMappingId = this._acRouter.snapshot.params['mid'];
   
   /* this._authService.getFacultyById(this.facultyId).subscribe(
      data =>{
           this.faculty = data;
      }
    )*/
    
  }
  public onFileChanged(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];
  }

  onUpload() {

    console.log(this.instruction);
    console.log(this.dueDate);
    console.log(this.score);
    console.log("hello")
    let date = stringify(this.dueDate);
    let facultyMapping = new FacultyMapping(this.facultyMappingId,null,null,null,null)
    let assignment = new Assignment(null,facultyMapping,null,this.instruction,null,this.dueDate.toString(),this.score);
    this._uploadAssignment.uploadFile(this.selectedFile,assignment).subscribe(
      data => {
        console.log(data);

        this._router.navigate(['/faculty',this.facultyId]);
        
      },
      err => {
        console.log(err)
      }
    )

  
  }
}
