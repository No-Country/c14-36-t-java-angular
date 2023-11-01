import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MetricsRoutingModule } from './metrics-routing.module';
import { MetricasComponent } from './metricas.component';
import { NgChartsModule } from 'ng2-charts';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    MetricasComponent
  ],
  imports: [
    CommonModule,
    MetricsRoutingModule,
    NgChartsModule,
    SharedModule
  ]
})
export class MetricsModule { }
