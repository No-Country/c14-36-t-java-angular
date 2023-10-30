import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InfoUserRoutingModule } from './info-user-routing.module';
import { InfoUserComponent } from './info-user.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    InfoUserComponent,
  ],
  imports: [
    CommonModule,
    InfoUserRoutingModule,
    ReactiveFormsModule
  ]
})
export class InfoUserModule { }
