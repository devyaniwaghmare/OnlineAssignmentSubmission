import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { FacultyComponent } from 'src/app/faculty/faculty/faculty.component';
import { Course } from 'src/app/model/course.model';
import { Faculty } from 'src/app/model/faculty';
import { FacultyMapping } from 'src/app/model/faculty-mapping';
import { Subjects } from 'src/app/model/subjects';
import { CoursesService } from 'src/app/services/courses.service';
import { FacultyMappingService } from 'src/app/services/faculty-mapping.service';
import { FacultyService } from 'src/app/services/faculty.service';
import { SubjectsService } from 'src/app/services/subjects.service';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private router:Router,
    private _courseService:CoursesService,
    private _subjectService:SubjectsService,
    private _facultyService:FacultyService,
    private _mappingService:FacultyMappingService) { }

  courseList:Course[];
  subjectList:Subjects[];
  facultyList:Faculty[];
  facultyMapping:FacultyMapping[];
  ngOnInit(): void {

    //------------courseService-------------------//
    this._courseService.getCourseDetails().subscribe(
      data => {
         // console.log(data)
          this.courseList =  data ;
      },
      err =>  console.log(err)
    )

     //------------subjectsService-------------------//
    this._subjectService.getSubjects().subscribe(
      data => {
        this.subjectList = data;
      },
      err => {
        console.log(err)
      }
    )

    //------------facultyService------------------//
    this._facultyService.getFaculty().subscribe(
      faculty => {
        this.facultyList = faculty;
      },
      err => {
        console.log(err);
      }
    )

     //------------faculty mapping------------------//
    this._mappingService.getMappingDetails().subscribe(
      data => {

        this.facultyMapping = data
        console.log(this.facultyMapping);
      },
      err => console.log(err)
     )
  }
 
   
  courseDetails(course:number)
  {
    
     console.log(course)
     this.router.navigate(['/courseDetails',course])
  }

  addCourse()
  {
      console.log("hello");
      this.router.navigate(['/addCourse']);
  }

  updateCourse(courseId:number)
  {
      this.router.navigate(['/updateCourse',courseId]);
  }

  deleteCourse(courseId:number)
  {
     this._courseService.deleteCourse(courseId).subscribe(
       data => {
         console.log(data);
         this.ngOnInit();
       },
       err=>{
         console.log(err);
       }
     )
     this.router.navigate(['/admin']);
  }
  addSubject()
  {
    this.router.navigate(['/addNewSubject']);
  }
  deleteSubject(subjectId:number)
  {
    this._subjectService.deleteSubjectById(subjectId).subscribe(
      data => {
        console.log(data);
        this.ngOnInit();
      },
      err=>{
        console.log(err)
      }
    )
    this.router.navigate(['/admin']);
  }

  //-------------- faculty operations----------------//
  addNewFaculty(){
    
    this.router.navigate(['/addNewFaculty']);
  }

  deleteFaculty(facultyId:number){

    this._facultyService.deleteFaculty(facultyId).subscribe(
      data => {

        console.log(data);
        this.ngOnInit();
      },
      err => {

        console.log(err)
      }
    )
  }
  updateFacultyDetails(facultyId:number)
  {
    console.log(facultyId);
    this.router.navigate(['/updateFacultyDetails',facultyId]);
  }

   //-------------- students ----------------//
  studentDetails(courseId:number)
  {
       this.router.navigate(['/studentDetails',courseId])
  }

     //-------------- faculty mapping ----------------//

     addFacultyMapping(){

      this.router.navigate(['/addmapping'])
     }
 
     delteFacultyMapping(mid:number)
     {

      this._mappingService.deleteFacultyMappingById(mid).subscribe(
        data => {
          console.log(data)
          this.ngOnInit()
        },
        err => {
          console.log(err)
        }
      )
     }

     updateFacultyMapping(mid:number)
     {

     }
}
