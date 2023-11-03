import { Component, Input, OnInit } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { DashboardService } from 'src/app/services/dashboard.service';
import { TokenService } from 'src/app/services/token.service';


@Component({
  selector: 'app-metricas',
  templateUrl: './metrics.component.html',
  styleUrls: ['./metrics.component.scss'],
})
export class MetricasComponent implements OnInit{
  saldo: number = 0;
  cvu: number = 0;
  transacciones: any[] = [];
  payments:any[] = [];
  datosTransaccionesSegmentados: any;
  isLoading: boolean = true;
  idAccount: string = "";
  showcvu: boolean = false;

  // Doughnut
  public doughnutChartLabels: string[] = [];
  public doughnutChartDatasets: ChartConfiguration<'doughnut'>['data']['datasets'] = [{data: []}];
  public doughnutChartOptions: ChartConfiguration<'doughnut'>['options'] = {responsive: true};
  public chartColors: any[] = [{backgroundColor:["#ffc107", "#6FC8CE", "#FAFFF2", "#FFFCC4", "#B9E8E0"] }];

  constructor(
    private dashboardService:DashboardService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
     this.isLoading = true;
     const userDataDecoded = this.tokenService.getTokenDecoded();
     const idUser = userDataDecoded.id;
     (async()=>{
      await this.obtenerDatosPorUUID(idUser);
      await this.obtenerTransaccionesPorAccountId();
      console.log("transacciones despues del request",this.transacciones)
      await this.obtenerPagosPorAccountId();
      await this.obtenerDatosCuentaPorAccountId(this.idAccount);
      this.showcvu = true;
      this.parsearData()
      this.isLoading= false;
     })()

  }

  async obtenerDatosPorUUID(uuid: string) {
    try {
        const response = await this.dashboardService.getUserDataById(uuid).toPromise();
        if (response.success && response.message === 'Usuario Encontrado') {
            console.log('Datos Usuario : ', response.data);
            const userData = response.data;
            this.idAccount = userData.idAccount;
        } else {
            console.log('Error', 'No se encontró al usuario', 'error');
        }
    } catch (error) {
        console.error('Error', 'No se pudieron obtener los datos', error);
    }
}

  async obtenerTransaccionesPorAccountId() {
    try {
      const response = await this.dashboardService.getTransactionsByAccountId(this.idAccount);
      console.log('Transacciones: ', response);

      if (response.data.content && response.data.content.length > 0) {
        this.transacciones = response.data.content.sort((a: any, b: any) => {
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

  async obtenerPagosPorAccountId() {
    try {
      const response = await this.dashboardService
      .getPaymentsByAccountId(this.idAccount);
      console.log('Pagos: ', response);

      if (response.data.content && response.data.content.length > 0) {
        this.payments = Object.values(response.data.content);
        this.payments = response.data.content.sort((a: any, b: any) => {
          return (
            new Date(b.dateEmit).getTime() - new Date(a.dateEmit).getTime()
          );
        });
      } else {
        console.log('Información', 'No hay Pagos', 'info');
      }
    } catch (error) {
      console.log('Error', 'No se pudieron obtener los Pagos', 'error');
    }
  }

  async obtenerDatosCuentaPorAccountId(idAccount: string) {
    try {
      const response = await this.dashboardService.getAccountDataById(
        idAccount
      );
      console.log('Data de la cuenta:', response);
      this.saldo = response.data.totalAccount;
      this.cvu = response.data.cvu;
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
    for (const payment of this.payments) {
      const tipo = payment.type;
      const monto = payment.amount;
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
