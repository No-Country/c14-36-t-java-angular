import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/Pages/home/home.component';
import { DashboardComponent } from './component/Pages/dashboard/dashboard.component';

import { LoginComponent } from './component/Pages/login/login.component';
import { RegisterComponent } from './component/Pages/register/register.component';
import { HelpComponent } from './component/Pages/helpPages/help/help.component';
import { HelpRequestComponent } from './component/Pages/helpPages/help-request/help-request.component';
import { HelpResponseComponent } from './component/Pages/helpPages/help-response/help-response.component';
import { CreditCardComponent } from './component/Pages/credit-card/credit-card.component';
import { HelpQuestionResComponent } from './component/Pages/helpPages/help-question-res/help-question-res.component';
import { UserDashboardComponent } from './component/Pages/user-dashboard/user-dashboard.component';
import { InfoUserComponent } from './component/Pages/info-user/info-user.component';
import { TransactionComponent } from './component/Pages/transaction/transaction.component';
import { authGuard } from './guards/auth.guard';
import { loginGuard } from './guards/login.guard';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'user', component: UserDashboardComponent, canActivate:[authGuard],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: DashboardComponent },
      { path: 'credit-card', component: CreditCardComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'transfer', component:TransactionComponent},
      {path: 'help', component: HelpComponent,
        children: [
          { path: '', redirectTo: 'helpReq', pathMatch: 'full' },
          { path: 'helpReq', component: HelpRequestComponent },
          { path: 'helpRes/:id', component: HelpResponseComponent },
          { path: 'helpQuestionRes/:id', component: HelpQuestionResComponent },
        ],
      },
      { path: 'info-user', component:InfoUserComponent },
    ],
  },
  { path: 'login', component: LoginComponent, canActivate:[loginGuard]},
  { path: 'register', component: RegisterComponent, canActivate:[loginGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
