import { Component } from '@angular/core';
import { ChartConfiguration } from 'chart.js';


@Component({
  selector: 'app-metricas',
  templateUrl: './metricas.component.html',
  styleUrls: ['./metricas.component.scss'],
})
export class MetricasComponent {
  
  saldo: string = '1200.00';
  hogar: number = 1200;
  transporte: number = 250;
  servicios: number = 100;
  otros: number = 350;

  constructor() {}

  // Doughnut
  public doughnutChartLabels: string[] = [ 'Hogar', 'Transporte', 'Servicios', 'Otros' ];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [
    {data: [
      this.hogar,
      this.transporte,
      this.servicios,
      this.otros,
    ]}
    ];

  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: true
  };

  public chartColors: any[] = [
    { 
      backgroundColor:["#ffc107", "#6FC8CE", "#FAFFF2", "#FFFCC4", "#B9E8E0"] 
    }];

  parsearData(): void {
    console.log('Data parser');
  }
}
