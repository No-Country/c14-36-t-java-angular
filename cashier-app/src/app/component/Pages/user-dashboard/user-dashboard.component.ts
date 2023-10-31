import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
// import { AuthTimerService } from 'src/app/services/auth-timer.service';
// import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent {
  constructor (
    // private tokenService : TokenService, 
    private router:Router){}
  sidebarStatus!:boolean;
  @ViewChild('sideBar', { read: ElementRef }) sideBar!: ElementRef;

  updateSidebarState(newStatus:boolean){
    this.sidebarStatus = newStatus;
      this.sideBar.nativeElement.classList.toggle("sidebarCollapse")

  }
  ngOnInit(){
    // this.tokenService.watchToken().subscribe(res=>{
    //   if(!res){
    //     this.router.navigate(['login'])
    //   }
    // })
  }

}
