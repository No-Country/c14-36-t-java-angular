import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicePageRoutingModule } from './service-page-routing.module';
import { ServicePageComponent } from './service-page.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BillComponent } from './bill/bill.component';
import { TargetServiceComponent } from './target-service/target-service.component';


@NgModule({
  declarations: [
    ServicePageComponent,
    BillComponent,
    TargetServiceComponent
  ],
  imports: [
    CommonModule,
    ServicePageRoutingModule,
    SharedModule
  ]
})
export class ServicePageModule { }
