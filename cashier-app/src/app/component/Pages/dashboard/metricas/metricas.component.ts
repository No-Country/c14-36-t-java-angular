import { Component, Input, OnInit } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { DashboardService } from 'src/app/services/dashboard.service';


@Component({
  selector: 'app-metricas',
  templateUrl: './metricas.component.html',
  styleUrls: ['./metricas.component.scss'],
})
export class MetricasComponent implements OnInit{
  saldo: number = 0;
  cvu: number = 0;
  transacciones: any[] = [];
  datosTransaccionesSegmentados: any;

  constructor(private dashboardService:DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.saldo$.subscribe((saldo) => {
      this.saldo = saldo;
    });

    this.dashboardService.cvu$.subscribe((cvu) => {
      this.cvu = cvu;
    });
  
    this.dashboardService.transacciones$.subscribe((transacciones) => {
      this.transacciones = transacciones;
    });

    this.datosTransaccionesSegmentados = this.obtenerSaldosPorTipo();
    console.log('Montos por tipo (objeto):', this.datosTransaccionesSegmentados);
    // Extraer los tipos de transacción del arreglo datosTransaccionesSegmentados
    this.doughnutChartLabels = this.datosTransaccionesSegmentados.map((item: any) => item.tipo);

  // Verificar si se han asignado correctamente
  console.log('Tipos de transacción:', this.doughnutChartLabels);
  // Extraer los montos de datosTransaccionesSegmentados y asignarlos a doughnutChartDatasets
  this.doughnutChartDatasets[0].data = this.datosTransaccionesSegmentados.map((item:any) => item.monto);

  // Verificar si se han asignado correctamente
  console.log('Montos para el gráfico:', this.doughnutChartDatasets[0].data);
  }

  // Doughnut
  public doughnutChartLabels: string[] = [];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [
    {data: []}
    ];

  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: false
  };

  public chartColors: any[] = [
    { 
      backgroundColor:["#ffc107", "#6FC8CE", "#FAFFF2", "#FFFCC4", "#B9E8E0"] 
    }];

  parsearData(): void {
    console.log('Data parser');
  }

  showCvu():void{
    this.dashboardService.showCvu();
  }
  

  obtenerSaldosPorTipo() {
    const saldosPorTipo: { [tipo: string]: number } = {};

    // Recorre el arreglo de transacciones
    for (const transaccion of this.transacciones) {
      const tipo = transaccion.type;
      const monto = transaccion.amount;

      // Si el tipo ya existe en el objeto saldosPorTipo, suma el monto
      if (saldosPorTipo[tipo]) {
        saldosPorTipo[tipo] += monto;
      } else {
        // Si el tipo no existe en el objeto, crea una nueva entrada
        saldosPorTipo[tipo] = monto;
      }
    }

    // Convierte el objeto en un arreglo de objetos
    const resultado = Object.keys(saldosPorTipo).map((tipo) => ({
      tipo,
      monto: saldosPorTipo[tipo],
    }));

    return resultado;
  }
}
