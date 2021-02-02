import { Course } from "./course.model";
import { Faculty } from "./faculty";
import { Subjects } from "./subjects";

export class FacultyMapping {

    mid:number;
    courses:Course;
    faculty:Faculty;
    subject:Subjects;
    status:boolean;

    constructor(mid:number,courses:Course,faculty:Faculty,subjects:Subjects,status:boolean)
    {
        this.mid = mid;
        this.courses = courses;
        this.faculty = faculty;
        this.subject = subjects;
        this.status = status;
    }

}
