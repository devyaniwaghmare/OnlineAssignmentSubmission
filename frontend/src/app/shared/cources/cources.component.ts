import { Component, OnInit } from '@angular/core';
import { CoursesService } from 'src/app/services/courses.service';

@Component({
  selector: 'app-cources',
  templateUrl: './cources.component.html',
  styleUrls: ['./cources.component.css']
})
export class CourcesComponent implements OnInit {

  cources = []
  constructor(private _courseService:CoursesService) { }

  ngOnInit(): void {
    this._courseService.getCourseDetails()
    .subscribe(
      res => { this.cources = res ;
      console.log(this.cources)
    },
      err => console.log(err)
    )
  }

}
