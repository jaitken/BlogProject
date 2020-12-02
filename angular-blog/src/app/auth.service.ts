import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from './login-request';
import { MessageResponse } from './message-response';
import { SignupRequest } from './signup-request';
import { TokenResponse } from './token-response';

const httpOptions = {
  headers: new HttpHeaders({"Content-Type": "application/json"})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private client: HttpClient) { }

  login(req: LoginRequest): Observable<TokenResponse>{
    return this.client.post<TokenResponse>('http://localhost:8080/api/auth/signin', req, httpOptions);
  }
  
  register(req: SignupRequest): Observable<any>{
    return this.client.post<MessageResponse>('http://localhost:8080/api/auth/signup', req, httpOptions);
  }
}
