import { Component, EventEmitter, Input, Output} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IUserTarget } from 'src/app/interfaces/User.interface';
import { IAccount } from 'src/app/interfaces/account.interface';
import { ITransactionDTO } from 'src/app/interfaces/transaction.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { AccountService } from 'src/app/services/account.service';
import { TransferService } from 'src/app/services/transfer.service';

@Component({
  selector: 'app-selected-user',
  templateUrl: './selected-user.component.html',
  styleUrls: ['./selected-user.component.scss'],
  animations:[enterLateral,fadeAnimation],
})
export class SelectedUserComponent {
  @Input() viewStatus!:transactionView;
  @Input() accountData!:IAccount;
  @Input() userTarget!:IUserTarget;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() updateAccountData = new EventEmitter<IAccount>();
  transferForm!:FormGroup;
  userTargetAccount!:IAccount;
  currentDate=new Date();

  constructor(
    private fb:FormBuilder,
    private transferServ:TransferService,
    private accountServ:AccountService
  ){
    this.initTransferForm();
  }

  updateAlertStatus(statusResponse:boolean){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertSuccess:statusResponse,
      alertFail:!statusResponse,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
  /*_-------------------------------------------- creacion del form */
  initTransferForm(){
    this.transferForm = this.fb.group({
      reason:['varios',[Validators.required]],
      amount:[0, [Validators.required]],
    })
  }
  /* --------------------------------------------------iniciar transferencia */
  onTransferSubmit(){
    this.registerTransferTime();
    const data = this.transferForm.value as {reason:string, amount:number}
    const idDestination = this.userTarget.idAccount;
    const idOrigin = this.accountData.idAccount;
    const dataDTO:ITransactionDTO = {
      ...data,
      type:"TRANSFER",
      origin:idOrigin,
      destination:idDestination
    }

    this.transferServ.newTransfer(dataDTO).subscribe({
      next:(res)=>{
        this.updateAlertStatus(true);
        this.updateAccountDataToParent();
      },
      error(err){
        console.log(err)
        this.updateAlertStatus(false);
      }
    })
  }
  updateAccountDataToParent(){
    this.updateAccountData.emit(null);
    this.accountServ.getAccount(this.accountData.idAccount).subscribe({
      next:(res)=>{
        this.updateAccountData.emit(res);
      },
      error(err){console.log(err)}
    })
  }
  registerTransferTime(){
    this.currentDate= new Date();
  }

  resetViewStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertSuccess:false,
      alertFail:false,
      contact:false,
    };
    this.updateViews.emit(newStatus);
  }
}
