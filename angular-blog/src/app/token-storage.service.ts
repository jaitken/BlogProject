import { Injectable } from '@angular/core';
import { TokenResponse } from './token-response';

const userkey: string = "auth-user";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {


  constructor() { }

  saveUser(userdata: TokenResponse): void{
    window.sessionStorage.removeItem(userkey);
    window.sessionStorage.setItem(userkey, JSON.stringify(userdata));
  }

  getUser(): TokenResponse{
    return JSON.parse(window.sessionStorage.getItem(userkey));
  }

  signout(): void {
    window.sessionStorage.clear();
  }

}
