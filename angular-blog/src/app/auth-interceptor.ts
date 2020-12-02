import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenResponse } from './token-response';
import { TokenStorageService } from './token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
   
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       let userDetails: TokenResponse = this.tokenServ.getUser();
       
       if(userDetails){
        req = req.clone({headers: req.headers.set("Authorization", "Bearer "+userDetails.token)});
       }
       return next.handle(req);
    }

    constructor(private tokenServ: TokenStorageService){}

    
}