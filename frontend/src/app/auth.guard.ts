import { Injectable } from '@angular/core';
import { CanActivate,Route, Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { StudentService } from './services/student.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(private __authService:AuthService,private _router:Router){}
  canActivate():boolean{

   
    if(this.__authService.logginIn())
    {
      console.log("in canActivate()")
      return true;
    }

    else{

      this._router.navigate(['/login']);
      return false;
    }
  }
}
