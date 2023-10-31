import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class JwtInterceptorInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
    let cloneRequest = request;
    
    if(localStorage.getItem('token')){
      // cloneRequest = request.clone({
      //   // setHeaders:{update:{
      //   //   Authorization: localStorage.getItem('token')!
          
      //   // }
      //   }
      // })
    }
 
    return next.handle(cloneRequest);
  }
}
