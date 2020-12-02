import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginRequest } from '../login-request';
import { SignupRequest } from '../signup-request';
import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  req : LoginRequest = {
    username:"",
    password:""
  };

  isLoggedIn: boolean = false;

  constructor(private authServ: AuthService, private tokenServ: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if(this.tokenServ.getUser()){
      this.isLoggedIn = true;
    }
  }

  onClick(){
    this.authServ.login(this.req).subscribe(t => {
      this.tokenServ.saveUser(t);
      this.isLoggedIn = true;
      this.router.navigate(["entries"])
    });
  }

}
