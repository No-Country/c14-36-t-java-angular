import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/Pages/home/home.component';
import { DashboardComponent } from './component/Pages/dashboard/dashboard.component';

import { LoginComponent } from './component/Pages/login/login.component';
import { HelpComponent } from './component/Pages/helpPages/help/help.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'user', component: UserDashboardComponent, canActivate: [authGuard],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: DashboardComponent },
      { path: 'credit-card', component: CreditCardComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'metricas', component: MetricasComponent },
      { path: 'transfer', component: TransactionComponent },
      { path: 'promo', component: PromotionsComponent },
      { path: 'service', component: ServicePageComponent },
      { path: 'help', component: HelpComponent,
        children: [
          { path: '', redirectTo: 'helpReq', pathMatch: 'full' },
          { path: 'helpReq', component: HelpRequestComponent },
          { path: 'helpRes/:id', component: HelpResponseComponent },
          { path: 'helpQuestionRes/:id', component: HelpQuestionResComponent },
        ],
      },
      { path: 'info-user', component: InfoUserComponent },
    ],
  },
  { path: 'login', component: LoginComponent, canActivate: [loginGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [loginGuard] },
  { path: '**', component: Found404Component, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
