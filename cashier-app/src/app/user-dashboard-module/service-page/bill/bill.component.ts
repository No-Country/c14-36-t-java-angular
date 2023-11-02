import { Component, EventEmitter, Input, Output } from '@angular/core';
import { enterLateral, fadeAnimation } from 'src/app/animations/animation';
import { IAccount } from 'src/app/interfaces/account.interface';
import { IBillRes, IGetPayment } from 'src/app/interfaces/response.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss'],
  animations:[enterLateral,fadeAnimation]
})
export class BillComponent {
  @Input() viewStatus!:transactionView;
  @Input() accountData!:IAccount;
  @Output() updateViews = new EventEmitter<transactionView>();
  @Output() updatePaymentTarget = new EventEmitter<IGetPayment>();
  paymentData!: IGetPayment[];
  historyBill!:IBillRes[]

  constructor(
    private paymentServ:PaymentService,
  ){}
  updateFilterStatus(filterData: boolean) {
    const newStatus: transactionView = {
      ...this.viewStatus,
      filterData,
      filterCVU: !filterData,
      contact: false,
      alertSuccess: false,
      alertFail: false,
    };
    this.updateViews.emit(newStatus);
  }
  updateContactStatus() {
    const newStatus = {
      ...this.viewStatus,
      contact: true,
      alerts: false,
    };
    this.updateViews.emit(newStatus);
  }
  ngOnInit(){
    this.paymentServ.getAllPayments().subscribe({
      next:(res)=>{this.paymentData = res},
      error:(err)=>{console.log(err)}
    })
    const {idAccount} =this.accountData;
    this.paymentServ.getBill(idAccount).subscribe({
      next:(res)=>{
        this.historyBill=res.data.content;
      },
      error(err){console.log(err)}
    })
  }
  selectPayment(id:number){
    this.updateContactStatus();
    this.updatePaymentTarget.emit(this.paymentData[id])
  }
}
