import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { SignupRequest } from '../signup-request';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  confirmPassword: string;

  isLoggedIn: boolean = false;
  req: SignupRequest = {
    username:"",
    password:"",
    email:""
  };

  constructor(private tokenServ: TokenStorageService, private authServ: AuthService, private router: Router) { }

  ngOnInit(): void {
    //??? getUser instead of getToken
    if(this.tokenServ.getUser()){
      this.isLoggedIn = true;
    }
  }

  onSubmit(): void {
    if(this.req.password === this.confirmPassword){
      this.authServ.register(this.req).subscribe(msgResponse =>{
        this.router.navigate(["signin"]);
      });
    }
    
  }

}
