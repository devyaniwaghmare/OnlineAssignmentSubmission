import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Subjects } from 'src/app/model/subjects';
import { SubjectsService } from 'src/app/services/subjects.service';
@Component({
  selector: 'app-add-new-subject',
  templateUrl: './add-new-subject.component.html',
  styleUrls: ['./add-new-subject.component.css']
})
export class AddNewSubjectComponent implements OnInit {
  
  subjectName:string;
  subjectDescription:string;
  msg:string;
  flag1:boolean;
  flag2:boolean;
  constructor(private _subjectService:SubjectsService,private _route:Router) { }

  ngOnInit(): void {
  }

  addNewSubject(){
    
      console.log(this.subjectDescription);
      let newSUb = new Subjects(null,this.subjectName,this.subjectDescription);
      console.log(newSUb);
      this._subjectService.addNewSubject(newSUb).subscribe(
        data => {
          console.log(data);
          this.flag1=true;
          this.msg="subject added successfully";
          this._route.navigate(['/admin']);
        },
        err=>{
           if(err.status===400)
           {
             this.flag2=true;
             this.msg="subject already present";
             this.ngOnInit();
           }
        }
      )

      this.subjectName="";
      this.subjectDescription="";
  }
}
