import { FacultyMapping } from "./faculty-mapping";

export class Assignment {

     assignmentId:number;
     map:FacultyMapping;
     assignmentDocument:string;
     instruction:string;
     assignDate:string;
     dueDate:string;
     marks:number;
    

     constructor(assignmentId:number,
        map:FacultyMapping,
        assignmentDocument:string,
        instruction:string,
        assignDate:string,
        dueDate:string,
        marks:number,
       )
     {
        this.assignmentId = assignmentId;
        this.map = map;
        this.assignmentDocument = assignmentDocument,
        this.instruction = instruction;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.marks = marks;
       
     }
}
