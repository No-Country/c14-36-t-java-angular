import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BalanceComponent } from './balance/balance.component';
import { BillComponent } from '../user-dashboard-module/service-page/bill/bill.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { LoadingComponent } from './loading/loading.component';
import { SideBarAltComponent } from './side-bar-alt/side-bar-alt.component';
import { TargetServiceComponent } from '../user-dashboard-module/service-page/target-service/target-service.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    BalanceComponent,
    FooterComponent,
    HeaderComponent,
    LoadingComponent,
    SideBarAltComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports:[
    BalanceComponent,
    FooterComponent,
    HeaderComponent,
    LoadingComponent,
    SideBarAltComponent,
  ]
})
export class SharedModule { }
