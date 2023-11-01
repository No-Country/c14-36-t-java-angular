import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDashboardComponent } from './user-dashboard.component';

const routes: Routes = [
  { path:'', component:UserDashboardComponent, children:[
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', loadChildren:()=>import('./dashboard/dashboard.module').then(m=>(m.DashboardModule)) },
    { path: 'credit-card', loadChildren:()=>import('./credit-card/credit-card.module').then(m=>(m.CreditCardModule)) },
    { path: 'metricas', loadChildren:()=>import('./metrics/metrics.module').then(m=>(m.MetricsModule)) },
    { path: 'transfer', loadChildren:()=>import('./transactions/transactions.module').then(m=>(m.TransactionsModule)) },
    { path: 'promo', loadChildren:()=>import('./promotions/promotions.module').then(m=>(m.PromotionsModule)) },
    { path: 'service', loadChildren:()=>import('./service-page/service-page.module').then(m=>(m.ServicePageModule)) },
    { path: 'info-user', loadChildren:()=>import('./info-user/info-user.module').then(m=>(m.InfoUserModule)) },
    { path: 'help', loadChildren:()=>import('./help/help.module').then(m=>(m.HelpModule))},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserDashboardRoutingModule { }
