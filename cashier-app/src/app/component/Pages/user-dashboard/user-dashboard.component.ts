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

  UserData!: UserData;
  sidebarStatus!:boolean;

  constructor(private router: Router,
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.UserData = this.tokenService.getTokenDecoded();
    this.validateTimeToken(this.UserData);
  }
  @ViewChild('sideBar', { read: ElementRef }) sideBar!: ElementRef;

  updateSidebarState(newStatus:boolean){
    this.sidebarStatus = newStatus;
      this.sideBar.nativeElement.classList.toggle("sidebarCollapse")

  }
  validateTimeToken({exp}:UserData){
    const currentDate = new Date();
    const expirationDate =  new Date(exp*1000);
    if(currentDate > expirationDate){
      localStorage.removeItem('tokenCashier');
    }
  }

}
