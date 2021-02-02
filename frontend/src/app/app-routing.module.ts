import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCourseComponent } from './admin/add-course/add-course.component';
import { AddMappingComponent } from './admin/add-mapping/add-mapping.component';
import { AddNewFacultyComponent } from './admin/add-new-faculty/add-new-faculty.component';
import { AddNewSubjectComponent } from './admin/add-new-subject/add-new-subject.component';
import { AddSubjectsComponent } from './admin/add-subjects/add-subjects.component';
import { AdminComponent } from './admin/admin/admin.component';
import { CoursedetailsComponent } from './admin/coursedetails/coursedetails.component';
import { StudentDetailsComponent } from './admin/student-details/student-details.component';
import { SubmitAssignmentComponent } from './admin/submit-assignment/submit-assignment.component';
import { UpdateCourseComponent } from './admin/update-course/update-course.component';
import { UpdateFacultyComponent } from './admin/update-faculty/update-faculty.component';
import { UpdateStudentsComponent } from './admin/update-students/update-students.component';
import { AssignmentComponent } from './assignment/assignment.component';
import { AuthGuard } from './auth.guard';
import { FacultyComponent } from './faculty/faculty/faculty.component';
import { SeeSubmissionComponent } from './faculty/see-submission/see-submission.component';
import { CourcesComponent } from './shared/cources/cources.component';
import { HomeComponent } from './shared/home/home.component';
import { LoginComponent } from './shared/login/login.component';
import { RegisterComponent } from './shared/register/register.component';
import { StudentComponent } from './student/student/student.component';

const routes: Routes = [
   {
     path:'',
     redirectTo:'/courses',
     pathMatch:'full'
   }
  ,{
     path:'home',
     component:HomeComponent
   }
  ,{
    path:'login',
    component:LoginComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'admin/:id',
    component:AdminComponent,
    canActivate:[AuthGuard]

  },
  {
    path:'admin',
    component:AdminComponent,
    canActivate:[AuthGuard]

  },
  {
    path:'faculty/:id',
    component:FacultyComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'student/:id',
    component:StudentComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'student',
    component:StudentComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'courses',
    component:CourcesComponent
  },
  {
    path:'courseDetails/:id',
    component:CoursedetailsComponent

  },
  {
    path:'updateCourse/:id',
    component:UpdateCourseComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'updateCourse/:id',
    component:UpdateCourseComponent,
    data:{
      kind:'subject'
    },
    canActivate:[AuthGuard]
  },
  {
    path:'addSubjects/:id',
    component:AddSubjectsComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'addCourse',
    component:AddCourseComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'addNewSubject',
    component:AddNewSubjectComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'addNewFaculty',
    component:AddNewFacultyComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'updateFacultyDetails/:id',
    component:UpdateFacultyComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'studentDetails/:id',
    component:StudentDetailsComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'updateStudent/:id',
    component:UpdateStudentsComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'addmapping',
    component:AddMappingComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'addAssignment/:fid/:mid',
    component:AssignmentComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'submitAssignment/:sid/:aid',
    component:SubmitAssignmentComponent,
    canActivate:[AuthGuard]
  },
  {
    path:'SeeSubmissionComponent/:fid/:mid/:subjectName',
    component:SeeSubmissionComponent,
    canActivate:[AuthGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
