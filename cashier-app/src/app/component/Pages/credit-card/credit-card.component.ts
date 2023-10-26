import { Component, Input } from '@angular/core';
import { UserData } from 'src/app/interfaces/userData.inteface';

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.scss']
})
export class CreditCardComponent {
  childStatus = false;
  @Input() userDaTa!:UserData;
  showData(){
    this.childStatus = true;
  }
  receiveChildStatus(newStatus:boolean){
    console.log(newStatus);
    this.childStatus =newStatus;
  }
}
