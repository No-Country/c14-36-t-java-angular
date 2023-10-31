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
  isLoading: boolean = true;
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
    const idUser = userDataDecoded.id;
    console.log('idUser: ', idUser);
    console.log('token Decoded: ', userDataDecoded);

    const obtenerDatos = async () => {
      try {
        await this.obtenerDatosPorUUID(idUser);
        await this.obtenerTransaccionesPorAccountId();
        await this.obtenerDatosCuentaPorAccountId(this.idAccount);
        await this.parsearData();
        console.log('La función parsearData se completó.');
        this.isLoading = false;
      } catch (error) {
        console.error('Error:', error);
        this.isLoading = false;
      }
    };

    obtenerDatos();
  }

  /**
   * The function `obtenerDatosPorUUID` is an asynchronous function that takes a UUID as a parameter
   * and returns a Promise that resolves with void.
   * @param {string} uuid - A string representing the unique identifier of a user.
   * @returns The function `obtenerDatosPorUUID` returns a Promise of type `void`.
   */
  async obtenerDatosPorUUID(uuid: string): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      this.dashboardService.getUserDataById(uuid).subscribe(
        (response) => {
          if (response.success && response.message === 'Usuario Encontrado') {
            console.log('Datos Usuario0 : ', response.data);
            const userData = response.data;
            this.idAccount = userData.idAccount;
            resolve();
          } else {
            console.log('Error', 'No se encontró al usuario', 'error');
            reject('No se encontró al usuario'); 
          }
        },
        (error) => {
          console.error('Error al obtener datos de usuario:', error);
          reject(error); 
        }
      );
    });
  }

 /**
   * The function obtains transactions by account ID and sorts them by date.
   * @returns a Promise of type void.
   */
 async obtenerTransaccionesPorAccountId(): Promise<void> {
  return new Promise<void>(async (resolve, reject) => {
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
        resolve(); 
      } else {
        console.log('Información', 'No hay transacciones', 'info');
        reject('No hay transacciones');
      }
    } catch (error) {
      console.error('Error al obtener las transacciones', error);
      reject(error);
    }
  });
}
  

  /**
   * The function "obtenerDatosCuentaPorAccountId" retrieves account data by account ID and assigns the
   * total account balance and CVU to class variables.
   * @param {string} idAccount - The id of the account for which you want to obtain the data.
   * @returns a Promise that resolves to void.
   */
  async obtenerDatosCuentaPorAccountId(idAccount: string): Promise<void> {
    return new Promise<void>(async (resolve, reject) => {
      try {
        const response = await this.dashboardService.getAccountDataById(
          idAccount
        );
        console.log('Data de la cuenta:', response);
        this.saldo = response.totalAccount;
        console.log('Data de la cuenta:', this.saldo);
        this.cvu = response.cvu;
        this.showcvu = true;
        resolve(); 
      } catch (error) {
        console.error('Error al obtener los datos de la cuenta', error);
        reject(error);
      }
    });
  }

  parsearData(): Promise<void> {
    return new Promise<void>((resolve, reject) => {
      // Aquí realizarás tus operaciones asincrónicas, como obtener los datos.
  
      // Simulemos una operación asincrónica (puedes reemplazar esto con tu lógica real):
      setTimeout(() => {
        this.datosTransaccionesSegmentados = this.obtenerSaldosPorTipo();
  
        // Verifica si se obtuvieron los datos correctamente
        if (this.datosTransaccionesSegmentados) {
          console.log('Montos por tipo (objeto):', this.datosTransaccionesSegmentados);
  
          this.doughnutChartLabels = this.datosTransaccionesSegmentados.map((item: any) => item.tipo);
          console.log('Tipos de transacción:', this.doughnutChartLabels);
  
          this.doughnutChartDatasets[0].data = this.datosTransaccionesSegmentados.map((item: any) => item.monto);
          console.log('Montos para el gráfico:', this.doughnutChartDatasets[0].data);
  
          resolve(); // Resuelve la promesa una vez que se han procesado los datos.
        } else {
          // En caso de error, puedes rechazar la promesa y proporcionar información adicional sobre el error.
          reject('No se pudieron obtener los datos');
        }
      }, 2000);
    });
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
