import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

import { LoginComponent } from './component/login/login.component';
import { SharedModule } from './shared/shared.module';
import { UserDashboardModule} from './user-dashboard-module/user-dashboard.module';
import { RegisterComponent } from './component/register/register.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule,
    UserDashboardModule,
    SharedModule

  ],
  providers: [
    CookieService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
