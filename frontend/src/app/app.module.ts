import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './shared/register/register.component';
import { LoginComponent } from './shared/login/login.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { AdminComponent } from './admin/admin/admin.component';
import { FacultyComponent } from './faculty/faculty/faculty.component';
import { StudentComponent } from './student/student/student.component';
import { HomeComponent } from './shared/home/home.component';
import { CourcesComponent } from './shared/cources/cources.component';
import { FormsModule } from '@angular/forms';
import { ContentComponent } from './shared/content/content.component';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { StudentService } from './services/student.service';
import { CoursesService } from './services/courses.service';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './auth.guard';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { FacultyService } from './services/faculty.service';
import { CoursedetailsComponent } from './admin/coursedetails/coursedetails.component';
import { UpdateCourseComponent } from './admin/update-course/update-course.component';
import { AddSubjectsComponent } from './admin/add-subjects/add-subjects.component';
import { AddCourseComponent } from './admin/add-course/add-course.component';
import { AddNewSubjectComponent } from './admin/add-new-subject/add-new-subject.component';
import { AddNewFacultyComponent } from './admin/add-new-faculty/add-new-faculty.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UpdateFacultyComponent } from './admin/update-faculty/update-faculty.component';
import { StudentDetailsComponent } from './admin/student-details/student-details.component';
import { UpdateStudentsComponent } from './admin/update-students/update-students.component';
import { AddNewStudentComponent } from './admin/add-new-student/add-new-student.component';
import { AddMappingComponent } from './admin/add-mapping/add-mapping.component';
import { AssignmentComponent } from './assignment/assignment.component';
import { SubmitAssignmentComponent } from './admin/submit-assignment/submit-assignment.component';
import { SeeSubmissionComponent } from './faculty/see-submission/see-submission.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    AdminComponent,
    FacultyComponent,
    StudentComponent,
    HomeComponent,
    CourcesComponent,
    ContentComponent,
    CoursedetailsComponent,
    UpdateCourseComponent,
    AddSubjectsComponent,
    AddCourseComponent,
    AddNewSubjectComponent,
    AddNewFacultyComponent,
    UpdateFacultyComponent,
    StudentDetailsComponent,
    UpdateStudentsComponent,
    AddNewStudentComponent,
    AddMappingComponent,
    AssignmentComponent,
    SubmitAssignmentComponent,
    SeeSubmissionComponent
 
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [StudentService,FacultyService,CoursesService,AuthService,AuthGuard,
  {
    provide:HTTP_INTERCEPTORS,
    useClass:TokenInterceptorService,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
