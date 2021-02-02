import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-see-submission',
  templateUrl: './see-submission.component.html',
  styleUrls: ['./see-submission.component.css']
})
export class SeeSubmissionComponent implements OnInit {

  constructor(private _acRouter:ActivatedRoute,
    private _router:Router) { }

  subjectName : string;
  facultyId : number;
  mappingId : number;
  ngOnInit(): void {

    this.subjectName=this._acRouter.snapshot.params['subjectName'];
    this.facultyId=this._acRouter.snapshot.params['fid']
    this.mappingId=this._acRouter.snapshot.params['mid']
    console.log(this.subjectName)
    console.log(this.facultyId)
    console.log(this.mappingId)

  }

}
