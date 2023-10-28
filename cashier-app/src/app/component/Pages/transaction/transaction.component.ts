import { Component } from '@angular/core';
import { enterLateral, fadeAnimation } from '../../../animations/animation';
import { UserData } from 'src/app/interfaces/userData.inteface';
import { TokenService } from 'src/app/services/token.service';
import { AccountService } from 'src/app/services/account.service';
import { UserService } from 'src/app/services/user.service';
import { IAccount } from 'src/app/interfaces/account.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { IUserTarget } from 'src/app/interfaces/User.interface';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss'],
  animations: [fadeAnimation, enterLateral ],
})
export class TransactionComponent {
  showComponents={
    form:false,
    filterData:true,
    filterCVU:false,
    formResult:false,
    contact:false,
    alerts:false
  }
  userTarget!:IUserTarget;

  userData!:UserData;
  accountData!:IAccount;

  constructor(
    private tokenServ:TokenService,
    private accountServ:AccountService,
    private userServ:UserService,
  ){}



  ngOnInit(){
    this.userData = this.tokenServ.dataUser;
    const {id} = this.userData;
    this.userServ.getUser(id).subscribe({
      next:({data})=>{
        this.accountServ.getAccount(data.idAccount).subscribe({
          next:(res)=>(this.accountData = res),
          error(err){console.log(err)}
        })
      },
      error(err){console.log(err)}
    })
  }
  updateViewStatus($event:transactionView){
    this.showComponents = $event;
  }
  updateIdTarget($event:IUserTarget){
    this.userTarget = $event;
  }
}
