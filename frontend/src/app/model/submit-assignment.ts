import { stringify } from "@angular/compiler/src/util";
import { Student } from "./student.model";

export class SubmitAssignment {

    submittedAssignment:number;
    emailId:string;
    solvedAssignmentDoct:string;
    submissionDate:string;
    status:boolean;
    obtaniedMarks:number;
    assignmentId:number;
    constructor(submittedAssignment:number, emailId:string,solvedAssignmentDoct:string,submissionDate:string,status:boolean,obtaniedMarks:number,assignmentId:number)
    {
        this.submittedAssignment=submittedAssignment;
        this.emailId = emailId;
        this.solvedAssignmentDoct=solvedAssignmentDoct;
        this.submissionDate=submissionDate;
        this.status=status;
        this.obtaniedMarks=obtaniedMarks;
        this.assignmentId=assignmentId;
    }
}
