import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicePageRoutingModule } from './service-page-routing.module';
import { ServicePageComponent } from './service-page.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    ServicePageComponent
  ],
  imports: [
    CommonModule,
    ServicePageRoutingModule,
    SharedModule
  ]
})
export class ServicePageModule { }
