import { Component} from '@angular/core';
import { ICard } from 'src/app/interfaces/account.interface';
import { AccountService } from 'src/app/services/account.service';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.scss']
})
export class CreditCardComponent {
  childStatus = false;
  cardData!:ICard;
  constructor(
    private userServ:UserService,
    private tokenServ:TokenService,
    private accountServ:AccountService
    ){}

  ngOnInit(){
    const {id} = this.tokenServ.getTokenDecoded() || {id:""};
    this.userServ.getUser(id).subscribe({
      next:({data})=>{
        this.accountServ.getCard(data.idCard).subscribe({
          next:(res)=>{this.cardData = res.data},
          error: (err)=>(console.log(err)),
          complete:()=>(console.log("consumo de creditcard exitoso.")),
        })
      },
      error:(err)=>(console.log(err)),
    })
  }

  showData(){
    this.childStatus = true;
  }

  receiveChildStatus(newStatus:boolean){
    console.log(newStatus);
    this.childStatus =newStatus;
  }
  showLastDigit(number:string){
    const lastDigits = number.slice(-4);
    return lastDigits;
  }
}
