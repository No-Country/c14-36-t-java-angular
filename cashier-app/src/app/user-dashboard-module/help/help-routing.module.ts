import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HelpComponent } from './help.component';
import { HelpRequestComponent } from './help-request/help-request.component';
import { HelpResponseComponent } from './help-response/help-response.component';
import { HelpQuestionResComponent } from './help-question-res/help-question-res.component';

const routes: Routes = [
  { path: '', component: HelpComponent,
    children: [
      { path: '', redirectTo: 'helpReq', pathMatch: 'full' },
      { path: 'helpReq', component: HelpRequestComponent },
      { path: 'helpRes/:id', component: HelpResponseComponent },
      { path: 'helpQuestionRes/:id', component: HelpQuestionResComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HelpRoutingModule {}
