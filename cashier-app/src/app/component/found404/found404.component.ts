import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-found404',
  templateUrl: './found404.component.html',
  styleUrls: ['./found404.component.scss']
})
export class Found404Component {
  hasToken = false;
  constructor(private router:Router){
    this.hasToken = localStorage.getItem('token') !== null;
  }

  redirect(){
    if(this.hasToken){
      this.router.navigate(['user'])
    }
    else{
      this.router.navigate(['login'])
    }
  }
}
