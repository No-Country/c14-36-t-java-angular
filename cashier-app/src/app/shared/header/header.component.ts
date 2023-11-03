import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from 'src/app/interfaces/userData.inteface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Input() userData!:UserData;
  constructor(private route: Router) { }

  ngOnInit(): void {

  }

  pipeNameData(name:string){
    const names = name.split(" ");
    const [firstName, lastname] = names;
    return (firstName.charAt(0)+lastname.charAt(0)).toUpperCase();
  }

  userInfo():void{
    this.route.navigate(["/user/info-user"], { state: { userData: this.userData } })
  }

  logOutUser() {
    localStorage.clear();
    this.route.navigate(["login"])
  }
}
