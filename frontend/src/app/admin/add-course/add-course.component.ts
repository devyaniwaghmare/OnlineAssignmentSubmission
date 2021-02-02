import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/model/course.model';
import { Subjects } from 'src/app/model/subjects';
import { CoursesService } from 'src/app/services/courses.service';
import { SubjectsService } from 'src/app/services/subjects.service';
import { Router,ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-add-course',
  templateUrl : './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  constructor(private _subjectService:SubjectsService,
    private _courseService:CoursesService,
    private _router:Router) { }
  subjectList:SubjectList[]= new Array();
  selectedSubjects:Subjects[] = new Array();
  selected:boolean;
  courseName:string;
  ngOnInit(): void {

    this._subjectService.getSubjects().subscribe(
      data => {
        for(let s of data)
        {
          //console.log(s.subjectId);
          let sub = new SubjectList(s.subjectId,s.subjectName,false);
          this.subjectList.push(sub);
          
        }
      },
      err=>{
         console.log(err);
      }
    )
  }

  onchange()
  {
    
  }
  addCourse(){

    for(let sub of  this.subjectList)
    {
        if(sub.isselected===true)
        {
         this.selectedSubjects.push( new Subjects(sub.subjectId,sub.subjectName,null));
         console.log(this.selectedSubjects)
        }
    }
    //console.log(this.courseName);
    let newCourse = new Course(null,this.courseName,this.selectedSubjects);
    console.log(newCourse)
    this._courseService.addCourse(newCourse).subscribe(
      data =>{
            console.log(data);
            this._router.navigate(['/admin']);

      },
      err=>{
           console.log(err)
      }
    )

  }
}
class SubjectList
   {
      subjectId:number;
      subjectName:string;
      isselected:boolean;

      constructor(subjectId:number,subjectName:string,isselected:false)
      {
           this.subjectId = subjectId,
           this.subjectName=subjectName,
           this.isselected=isselected
      }
   }