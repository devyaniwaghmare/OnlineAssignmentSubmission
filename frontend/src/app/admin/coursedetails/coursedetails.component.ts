import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/model/course.model';
import { CoursesService } from 'src/app/services/courses.service';
@Component({
  selector: 'app-coursedetails',
  templateUrl: './coursedetails.component.html',
  styleUrls: ['./coursedetails.component.css']
})
export class CoursedetailsComponent implements OnInit {

  constructor(private _route : ActivatedRoute,private _courseService:CoursesService) { }
  
   course:Course;
  ngOnInit(): void {

    const id =  +this._route.snapshot.params['id'];
    this._courseService.getCourseDetailsById(id).subscribe(
      data => {
        this.course=data
       // console.log(this.course.courseId)
      },
      err=>console.log("no course found")
    )
  }

}
