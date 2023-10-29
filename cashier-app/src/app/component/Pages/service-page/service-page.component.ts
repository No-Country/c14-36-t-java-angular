import { Component } from '@angular/core';
import { fadeAnimation, enterLateral } from 'src/app/animations/animation';
import { IUserTarget } from 'src/app/interfaces/User.interface';
import { IAccount } from 'src/app/interfaces/account.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { UserData } from 'src/app/interfaces/userData.inteface';
import { AccountService } from 'src/app/services/account.service';
import { TokenService } from 'src/app/services/token.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.scss'],
  animations: [fadeAnimation, enterLateral]
})
export class ServicePageComponent {
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
    console.log(this.userData)
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
