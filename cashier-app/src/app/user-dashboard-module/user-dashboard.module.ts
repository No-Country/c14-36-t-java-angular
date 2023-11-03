import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserDashboardComponent } from './user-dashboard.component';
import { UserDashboardRoutingModule } from './user-dashboard-routing.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [UserDashboardComponent],
  imports: [
    CommonModule,
    UserDashboardRoutingModule,
    SharedModule,
  ]
})
export class UserDashboardModule { }
