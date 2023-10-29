import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
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
  transferForm!:FormGroup;
  userTargetAccount!:IAccount;

  constructor(
    private fb:FormBuilder,
    private transferServ:TransferService
  ){
    this.initTransferForm();
  }

  updateAlertStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alerts:true,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
  /*_-------------------------------------------- creacion del form */
  initTransferForm(){
    this.transferForm = this.fb.group({
      issue:['varios',[Validators.required]],
      amount:[0, [Validators.required]],
    })
  }
  onTransferSubmit(){
    const data = this.transferForm.value as {issue:string, amount:number}
    const dataDTO= {
      amount:50,
      state:"DONE",
      type:"DEPOSIT"
    }
    console.log(dataDTO);
    const {idAccount} = this.userTarget;
    console.log(idAccount)
    this.transferServ.newTransfer(idAccount,dataDTO).subscribe({
      next:(res)=>{
        console.log(res)
        this.updateAlertStatus();
      },
      error(err){console.log(err)}
    })
  }

}
