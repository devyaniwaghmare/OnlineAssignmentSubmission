import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { Course } from 'src/app/model/course.model';
import { Faculty } from 'src/app/model/faculty';
import { FacultyMapping } from 'src/app/model/faculty-mapping';
import { Subjects } from 'src/app/model/subjects';
import { AuthService } from 'src/app/services/auth.service';
import { CoursesService } from 'src/app/services/courses.service';
import { FacultyMappingService } from 'src/app/services/faculty-mapping.service';
import { FacultyService } from 'src/app/services/faculty.service';
import { SubjectsService } from 'src/app/services/subjects.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-mapping',
  templateUrl: './add-mapping.component.html',
  styleUrls: ['./add-mapping.component.css']
})
export class AddMappingComponent implements OnInit {

  constructor(private _facultyService:FacultyService,
    private _courseService:CoursesService,
    private _subjectService:SubjectsService,
    private _facultyMapping:FacultyMappingService,
    private _authService:AuthService,
    private _router:Router) { }
 

  facultyList:Faculty[];
  coursList:Course[];
  subjectsList:Subjects[];
  facultyId:any;
  couserId:any;
  subjectId:any;
  flag:boolean;
  ngOnInit(): void {

    this._facultyService.getFaculty().subscribe(
      data => {
          this.facultyList=data;
          console.log(this.facultyList)
      },
      err=>{
             console.log(err)
      }
    )

    this._courseService.getCourseDetails().subscribe(
      data => {
        this.coursList = data
        console.log(this.coursList)
      
      },
      err => {
        console.log(err)
      }
    )   

    this._subjectService.getSubjects().subscribe(
      data => {
        console.log(data)
        this.subjectsList = data;
      }
    )

  }
 
  validateCourse(courseId:any){
    
   
    this._courseService.getCourseDetailsById(this.couserId).subscribe(
      data =>{
        this.subjectsList = data.subjects
        this.flag=true;
      } ,
      err => {
        console.log(err)
      }
    )
  }


  assignFaculty(){

  
    console.log(this.facultyId)
    console.log(this.subjectId);
    console.log(this.couserId)
    let addedFaculty = new Faculty(this.facultyId,null,null,null,null,null,null,null);
    let addedSubject = new Subjects(this.subjectId,null,null);
    let addedCourse = new Course(this.couserId,null,null);
    this._facultyMapping.addFacultyMapping(new FacultyMapping(null,addedCourse,addedFaculty,addedSubject,null)).subscribe(
      data => {
        console.log(data)
        this._router.navigate(['/admin']);
        
      },
      err => {
        console.log(err)
      }
    )
  }
 
 
}
