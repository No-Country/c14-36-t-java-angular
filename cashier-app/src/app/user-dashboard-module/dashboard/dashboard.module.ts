import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PipeTransformPipe } from 'src/app/pipes/pipe-transform.pipe';
import { FormsModule } from '@angular/forms';
import { DateTemporaryPipe } from 'src/app/pipes/dateTemporary.pipe';



@NgModule({
  declarations: [
    DashboardComponent,
    PipeTransformPipe,
    DateTemporaryPipe
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    SharedModule,
    FormsModule
  ]
})
export class DashboardModule { }
