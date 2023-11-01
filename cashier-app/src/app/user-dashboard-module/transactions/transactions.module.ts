import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionComponent } from './transaction.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [TransactionComponent],
  imports: [
    CommonModule,
    TransactionsRoutingModule,
    SharedModule
  ]
})
export class TransactionsModule { }
