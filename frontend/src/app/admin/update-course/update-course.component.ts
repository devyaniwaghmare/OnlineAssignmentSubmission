import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/model/course.model';
import { CoursesService } from 'src/app/services/courses.service';
import { SubjectsService } from 'src/app/services/subjects.service';
@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  msg:any;
  constructor(private _router:Router,
    private _acRouter:ActivatedRoute,
    private _courseService:CoursesService,
    private _subjectService:SubjectsService) { }
  selectedCourse:Course=new Course(null,null,null);
  id:number;
  ngOnInit(): void {

     this.id =  +this._acRouter.snapshot.params['id'];
    console.log(this.id);
    this._courseService.getCourseDetailsById(this.id).subscribe(
      data => {
           this.selectedCourse=data;
           //console.log(this.selectedCourse.courseName)
           console.log(this.selectedCourse)
      },
      err=>{
        console.log(err)
      }
    )
  }


  removeSubjectFromCourse(courseId:number,subjectId:number)
  {
       console.log(courseId)
       console.log(subjectId)
       this._courseService.removeCourseSubjectEntry(courseId,subjectId).subscribe(
         data => {
           console.log(data)
         },
         err => {
             console.log(err);
         }
       )
  }
  addSubjectToCourse(courseId:number)
  {
    console.log(courseId);
    this._router.navigate(['/addSubjects',courseId]);
    const sid =  +this._acRouter.snapshot.params['sid'];
    console.log(sid);

  }

  updateCourse(courseId:number)
  {
    this._router.navigate(['/admin'])
  }

}
