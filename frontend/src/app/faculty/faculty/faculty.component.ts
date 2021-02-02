import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Faculty } from 'src/app/model/faculty';
import { FacultyMapping } from 'src/app/model/faculty-mapping';
import { Subjects } from 'src/app/model/subjects';
import { AuthService } from 'src/app/services/auth.service';
import { FacultyMappingService } from 'src/app/services/faculty-mapping.service';
@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.css']
})
export class FacultyComponent implements OnInit {

  constructor(private _router:Router,
    private _acRouter:ActivatedRoute,
    private _authService:AuthService,
    private _facultyMapping:FacultyMappingService) { }


  facultyId:number;
  faculty = new Faculty(null,null,null,null,null,null,null,null);
  subjectList:Subjects;
  mappingEntry = new Array();
  facultyMapping = new Array();
  status:boolean;
  ngOnInit(): void {

    this.facultyId = this._acRouter.snapshot.params['id'];
    console.log(this.facultyId);
    this._authService.getFacultyById(this.facultyId).subscribe(
      data => {
       // console.log(data)
        this.faculty  = data;
       
      }
    )

    this._facultyMapping.getMappingDetails().subscribe(
      data => {

        for(let f of data)
        {
          
            if(f.faculty.fid==this.facultyId)
            {
              this.mappingEntry.push(f.mid);
              this._facultyMapping.getFacultyMappingById(f.mid).subscribe(
                data => {
                  this.facultyMapping.push(data);
                  console.log(this.facultyMapping)
                }
              )
            }
            console.log(this.mappingEntry[0]);
          
        }

      },
      err => {
        console.log(err)
      }
    )

  }

  addAssignment(){
    console.log("hello")
  }

}
