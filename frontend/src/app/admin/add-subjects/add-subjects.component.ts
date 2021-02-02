import { Component, OnInit } from '@angular/core';
import { SubjectsService } from 'src/app/services/subjects.service';
import { Router ,ActivatedRoute} from '@angular/router';
import { Subjects } from 'src/app/model/subjects';
import { CoursesService } from 'src/app/services/courses.service';
import { Course } from 'src/app/model/course.model';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-add-subjects',
  templateUrl: './add-subjects.component.html',
  styleUrls: ['./add-subjects.component.css']
})
export class AddSubjectsComponent implements OnInit {

  constructor(private _subjectService:SubjectsService,private _router:Router,private _acRouter:ActivatedRoute,private _courseService:CoursesService) { }
  subjectList:Subjects[];
  subjectList1: Subjects[] = new Array();
  count : number = 0;
  ngOnInit(): void {
    
    this._subjectService.getSubjects().subscribe(
      data => {

        console.log(data)
        this.subjectList=data;
      },
      err => {
        console.log(err)
      }
    )

  }

  addSubjectToCourse(subjectId:number) {

    let selectedSubject:Subjects;
    const courseId = this._acRouter.snapshot.params['id'];
    let course : Course;
   

    this._subjectService.getSubjectById(subjectId).subscribe(
      data => {
        
         //console.log(data);
       selectedSubject = data;

       //---------------------------------------------------------------------------//
       this._courseService.getCourseDetailsById(courseId).subscribe(
        c => {
           course = c;
           this.subjectList1=course.subjects;
           this.subjectList1.push(selectedSubject);
           let updatedCourse = new Course(course.courseId,course.courseName,this.subjectList1);
            this._courseService.updateCourse(courseId,updatedCourse).subscribe(
            courseAfterUpdation => {
              
                console.log("course after updation")
                 console.log(courseAfterUpdation);
                 this._router.navigate(['/updateCourse',courseId]);
            },
            err => {
               console.log(err);
            }
          )

        },
        err => {
          console.log(err);
        }  
     )  
     //---------------------------------------------------------------------------//

      },
      err => {
        console.log(err);
      }
    )
    
  }
}
