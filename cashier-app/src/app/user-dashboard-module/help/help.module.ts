import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HelpRoutingModule } from './help-routing.module';
import { HelpComponent } from './help.component';
import { HelpRequestComponent } from './help-request/help-request.component';
import { HelpResponseComponent } from './help-response/help-response.component';
import { HelpQuestionResComponent } from './help-question-res/help-question-res.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HelpComponent,
    HelpRequestComponent,
    HelpResponseComponent,
    HelpQuestionResComponent
  ],
  imports: [
    CommonModule,
    HelpRoutingModule,
    ReactiveFormsModule
  ]
})
export class HelpModule { }
