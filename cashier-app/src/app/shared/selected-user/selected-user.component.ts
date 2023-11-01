import { Component, EventEmitter, Input, Output} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IUserTarget } from 'src/app/interfaces/User.interface';
import { IAccount } from 'src/app/interfaces/account.interface';
import { ITransactionDTO } from 'src/app/interfaces/transaction.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
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
  transferForm!:FormGroup;
  userTargetAccount!:IAccount;

  constructor(
    private fb:FormBuilder,
    private transferServ:TransferService
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
      },
      error(err){
        console.log(err)
        this.updateAlertStatus(false);
      }
    })
  }

}
