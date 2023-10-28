import { Component, Input, OnInit } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { DashboardService } from 'src/app/services/dashboard.service';
import { TokenService } from 'src/app/services/token.service';


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
  isLoading: boolean = false;
  idAccount: string = "";
  showcvu: boolean = false;

  // Doughnut
  public doughnutChartLabels: string[] = [];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [{data: []}];
  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {responsive: true};
  public chartColors: any[] = [{backgroundColor:["#ffc107", "#6FC8CE", "#FAFFF2", "#FFFCC4", "#B9E8E0"] }];

  constructor(private dashboardService:DashboardService, 
              private tokenService: TokenService) {}

  ngOnInit(): void {
     this.isLoading = true;
     const userDataDecoded = this.tokenService.getTokenDecoded();
     let idUser:any = userDataDecoded?.id;
     this.obtenerDatosPorUUID(idUser);
     setTimeout(() => {
       this.obtenerTransaccionesPorAccountId();
     }, 1000);
     setTimeout(() => {
       this.obtenerDatosCuentaPorAccountId(this.idAccount);
       this.showcvu = true;
     }, 1000);

     // Mostrar el indicador de carga.
     this.isLoading = false;
     this.parsearData()
  }

  obtenerDatosPorUUID(uuid: string) {
    this.dashboardService.getUserDataById(uuid).subscribe(
      (response) => {
        if (response.success && response.message === 'Usuario Encontrado') {
          console.log('Datos Usuario : ', response.data);
          const userData = response.data;
          this.idAccount = userData.idAccount;
        } else {
          console.log('Error', 'No se encontró al usuario', 'error');
        }
      },
      (error) => {
        console.log('Error', 'No se pudieron obtener los datos', 'error');
      }
    );
  }

  async obtenerTransaccionesPorAccountId() {
    try {
      const response = await this.dashboardService.getTransactionsByAccountId(
        this.idAccount
      );
      console.log('Transacciones: ', response);

      if (response.data && response.data.length > 0) {
        this.transacciones = Object.values(response.data);
        this.transacciones = response.data.sort((a: any, b: any) => {
          return (
            new Date(b.dateEmit).getTime() - new Date(a.dateEmit).getTime()
          );
        });
      } else {
        console.log('Información', 'No hay transacciones', 'info');
      }
    } catch (error) {
      console.log('Error', 'No se pudieron obtener las transacciones', 'error');
    }
  }

  async obtenerDatosCuentaPorAccountId(idAccount: string) {
    try {
      const response = await this.dashboardService.getAccountDataById(
        idAccount
      );
      console.log('Data de la cuenta:', response);
      this.saldo = response.totalAccount;
      this.cvu = response.cvu;
      this.showcvu = true;
      this.isLoading = false; 
    } catch (error) {
      console.error('Error al obtener los datos de la cuenta', error);
      this.isLoading = false; 
    }
  }

  

  parsearData(): void {
    setTimeout(() => {
      this.datosTransaccionesSegmentados = this.obtenerSaldosPorTipo();
      console.log(
        'Montos por tipo (objeto):',
        this.datosTransaccionesSegmentados
      );
      // Extraer los tipos de transacción del arreglo datosTransaccionesSegmentados
      this.doughnutChartLabels = this.datosTransaccionesSegmentados.map(
        (item: any) => item.tipo
      );
  
      // Verificar si se han asignado correctamente
      console.log('Tipos de transacción:', this.doughnutChartLabels);
      // Extraer los montos de datosTransaccionesSegmentados y asignarlos a doughnutChartDatasets
      this.doughnutChartDatasets[0].data = this.datosTransaccionesSegmentados.map(
        (item: any) => item.monto
      );
  
      // Verificar si se han asignado correctamente
      console.log('Montos para el gráfico:', this.doughnutChartDatasets[0].data);
      
    }, 2000);
    
    
  }

  showCvu():void{
    this.dashboardService.showCvu();
  }
  

  obtenerSaldosPorTipo() {
    const saldosPorTipo: { [tipo: string]: number } = {};
    for (const transaccion of this.transacciones) {
      const tipo = transaccion.type;
      const monto = transaccion.amount;
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
