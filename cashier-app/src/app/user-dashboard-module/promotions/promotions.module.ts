import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PromotionsRoutingModule } from './promotions-routing.module';
import { PromotionsComponent } from './promotions.component';


@NgModule({
  declarations: [
    PromotionsComponent
  ],
  imports: [
    CommonModule,
    PromotionsRoutingModule
  ]
})
export class PromotionsModule { }
