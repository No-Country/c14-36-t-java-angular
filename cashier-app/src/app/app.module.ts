import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './component/Pages/dashboard/dashboard.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { HomeComponent } from './component/Pages/home/home.component';
import { CardComponent } from './shared/card/card.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { LoginComponent } from './component/Pages/login/login.component';
import { RegisterComponent } from './component/Pages/register/register.component';
import { TransactionComponent } from './component/Pages/transaction/transaction.component';
import { CreditCardComponent } from './component/Pages/credit-card/credit-card.component';
import { MetricComponent } from './component/Pages/metric/metric.component';
import { HelpResponseComponent } from './component/Pages/helpPages/help-response/help-response.component';
import { HelpRequestComponent } from './component/Pages/helpPages/help-request/help-request.component';
import { HelpComponent } from './component/Pages/helpPages/help/help.component';
import { CreditCardChildComponent } from './component/Pages/credit-card/credit-card-child/credit-card-child.component';
import { HelpQuestionResComponent } from './component/Pages/helpPages/help-question-res/help-question-res.component';
import { InfoUserComponent } from './component/Pages/info-user/info-user.component';
import { SideBarAltComponent } from './shared/side-bar-alt/side-bar-alt.component';
import { UserDashboardComponent } from './component/Pages/user-dashboard/user-dashboard.component';
import { CookieService } from 'ngx-cookie-service';
import { PromotionsComponent } from './component/Pages/promotions/promotions.component';
import { Found404Component } from './component/Pages/found404/found404.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CardComponent,
    SidebarComponent,
    LoginComponent,
    RegisterComponent,
    TransactionComponent,
    MetricComponent,
    CreditCardComponent,
    CreditCardChildComponent,
    FooterComponent,
    HeaderComponent,
    DashboardComponent,
    HelpComponent,
    HelpResponseComponent,
    HelpRequestComponent,
    InfoUserComponent,
    SideBarAltComponent,
    UserDashboardComponent,
    HelpQuestionResComponent,
    PromotionsComponent,
    Found404Component,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    CookieService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
