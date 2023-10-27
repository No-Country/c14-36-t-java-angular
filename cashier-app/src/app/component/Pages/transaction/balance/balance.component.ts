import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IAccount } from 'src/app/interfaces/account.interface';
import { transactionView } from 'src/app/interfaces/transactionView.interface';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.scss']
})
export class BalanceComponent {
@Input() accountData!:IAccount;
@Input() viewStatus!:transactionView;
@Output() updateViews = new EventEmitter<transactionView>();

updateViewsStatus(){
  const {form} = this.viewStatus;
  const newStatus:transactionView = {
    ...this.viewStatus,
    form:!form,
    alerts:false,
    contact:false,
  };
  this.updateViews.emit(newStatus);
}
}
