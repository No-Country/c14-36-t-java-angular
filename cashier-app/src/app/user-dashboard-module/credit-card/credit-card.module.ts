import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreditCardRoutingModule } from './credit-card-routing.module';
import { CreditCardComponent } from './credit-card.component';
import { CreditCardChildComponent } from './credit-card-child/credit-card-child.component';


@NgModule({
  declarations: [
    CreditCardComponent,
    CreditCardChildComponent
  ],
  imports: [
    CommonModule,
    CreditCardRoutingModule
  ]
})
export class CreditCardModule { }
