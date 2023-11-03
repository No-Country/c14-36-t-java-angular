import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionComponent } from './transaction.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TransferFormComponent } from './transfer-form/transfer-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SelectedUserComponent } from './selected-user/selected-user.component';


@NgModule({
  declarations: [
    TransactionComponent,
    TransferFormComponent,
    SelectedUserComponent
  ],
  imports: [
    CommonModule,
    TransactionsRoutingModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class TransactionsModule { }
