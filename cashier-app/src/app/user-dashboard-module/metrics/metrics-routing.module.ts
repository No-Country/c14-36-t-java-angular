import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MetricasComponent } from './metrics.component';

const routes: Routes = [
  { path:'', component:MetricasComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MetricsRoutingModule { }
