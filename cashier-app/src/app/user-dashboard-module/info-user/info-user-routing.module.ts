import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InfoUserComponent } from './info-user.component';

const routes: Routes = [
  { path:'', component:InfoUserComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InfoUserRoutingModule { }
