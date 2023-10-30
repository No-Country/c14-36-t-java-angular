import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() {
    window.addEventListener('storage',()=>{
      this.tokenStatus.next(this.hasToken())
    })
   }
  private tokenStatus = new BehaviorSubject<boolean>(this.hasToken());

  public hasToken(){
    return localStorage.getItem('token') !== null;
  }

  watchToken(){
    return this.tokenStatus.asObservable();
  }
}
