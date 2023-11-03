import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthTimerService {

  constructor( private router:Router) {
    this.initTimer();
    this.snapshotActivity();
   }

  private timer!:NodeJS.Timeout;
  private readonly inactivityPeriod = 60000*5;

  private initTimer(){
    this.timer = setTimeout(() => {
      this.clearToken()
    }, this.inactivityPeriod);
  }

  private snapshotActivity(){
    window.addEventListener('keypress', ()=>{
      this.resetTimer()
    })
    window.addEventListener('click', ()=>{
      this.resetTimer()
    })
  }

  private resetTimer(){
    clearTimeout(this.timer);
    this.initTimer();
  }

  private clearToken(){
    localStorage.removeItem('tokenCashier');
    this.router.navigate(['login'])
  }
}
