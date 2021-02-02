export class Faculty {

    fid:number;
    firstName:string;
    lastName:string;
    phone:string;
    gender:string;
    emailId:string;
    DOB:Date;
    password:string;



    constructor(fid:number,firstName:string,lastName:string,phone:string,gender:string,emailId:string,DOB:Date,password:string){
            
        this.fid = fid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.emailId = emailId;
        this.DOB=DOB;
        this.password = password;
    }

}
