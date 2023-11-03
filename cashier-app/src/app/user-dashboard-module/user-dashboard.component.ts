import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from 'src/app/interfaces/userData.inteface';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent {
  @ViewChild('sideBar', { read: ElementRef }) sideBar!: ElementRef;

  UserData!: UserData;
  sidebarStatus!:boolean;

  constructor(
    private router:Router,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    const data = this.tokenService.getTokenDecoded();
    if(data){
      this.UserData = data;
    }
    else{
      localStorage.removeItem('tokenCashier')
      this.router.navigate(['login'])
    }
    this.validateTimeToken(this.UserData);
  }

  updateSidebarState(newStatus:boolean){
    this.sidebarStatus = newStatus;
      this.sideBar.nativeElement.classList.toggle("sidebarCollapse")

  }
  /* valida si el token expiro */
  validateTimeToken({exp}:UserData){
    const currentDate = new Date();
    const expirationDate =  new Date(exp*1000);
    if(currentDate > expirationDate){
      localStorage.removeItem('tokenCashier');
    }
  }

}
