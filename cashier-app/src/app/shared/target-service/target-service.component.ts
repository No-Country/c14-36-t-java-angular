import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IAccount } from 'src/app/interfaces/account.interface';
import { IGetPayment } from 'src/app/interfaces/response.interface';
import { IBillDTO } from 'src/app/interfaces/transaction.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-target-service',
  templateUrl: './target-service.component.html',
  styleUrls: ['./target-service.component.scss'],
  animations:[fadeAnimation, enterLateral]
})
export class TargetServiceComponent {
  constructor(
    private paymentServ:PaymentService
  ){}
  @Input() viewStatus!:transactionView;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Input() accountData !: IAccount;
  @Input() targetService !: IGetPayment;

  updateAlertStatus(){
    const newStatus:transactionView = {
      ...this.viewStatus,
      alertFail:true,
      contact:true,
    };
    this.updateViews.emit(newStatus);
  }
  createBill(){
    const bill:IBillDTO={
      amount:this.targetService.monto,
      bill_num:this.targetService.idFactura,
      bill_type:"servicio",
      origin:this.accountData.idAccount,
      type:"PAYMENT"
    }
    console.log(bill)
    this.paymentServ.newPayment(bill).subscribe({
      next:(res)=>{
        this.updateAlertStatus()
      },
      error:(err)=>{console.log(err)}
    })
  }
}
