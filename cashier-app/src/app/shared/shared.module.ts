import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BalanceComponent } from './balance/balance.component';
import { BillComponent } from './bill/bill.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { LoadingComponent } from './loading/loading.component';
import { SelectedUserComponent } from './selected-user/selected-user.component';
import { SideBarAltComponent } from './side-bar-alt/side-bar-alt.component';
import { TargetServiceComponent } from './target-service/target-service.component';
import { TransferFormComponent } from './transfer-form/transfer-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    BalanceComponent,
    BillComponent,
    FooterComponent,
    HeaderComponent,
    LoadingComponent,
    SelectedUserComponent,
    SideBarAltComponent,
    TargetServiceComponent,
    TransferFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports:[
    BalanceComponent,
    BillComponent,
    FooterComponent,
    HeaderComponent,
    LoadingComponent,
    SelectedUserComponent,
    SideBarAltComponent,
    TargetServiceComponent,
    TransferFormComponent
  ]
})
export class SharedModule { }
