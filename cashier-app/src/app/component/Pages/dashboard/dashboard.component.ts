import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DashboardService } from 'src/app/services/dashboard.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  saldo: number = 0;
  transacciones: any | [] = [];
  orderBy: string = 'asc';
  searchText: string = '';
  sortKey: string = '';
  cvu: number = 0;
  showcvu: boolean = false;
  idAccount: string = '';
  isLoading: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private dashboardService: DashboardService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    // Mostrar el indicador de carga.
    this.isLoading = true;
    const userDataDecoded = this.tokenService.getTokenDecoded();
    const idUser = userDataDecoded.id;
    console.log('idUser: ', idUser);
    console.log('token Decoded: ', userDataDecoded);
    this.obtenerDatosPorUUID(idUser);

    setTimeout(() => {
      this.obtenerTransaccionesPorAccountId();
    }, 1000);

    setTimeout(() => {
      this.obtenerDatosCuentaPorAccountId(this.idAccount);
    }, 1000);
    // Mostrar el indicador de carga.
    
    setTimeout(() => {
      this.isLoading= false;
    }, 1000);
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
      console.log('Data de la cuenta:', this.saldo);
      this.cvu = response.cvu;
      this.showcvu = true;
      this.isLoading = false; 
    } catch (error) {
      console.error('Error al obtener los datos de la cuenta', error);
      this.isLoading = false; 
    }
  }

  /*
    obtenerTransaccionesPorAccountId() {
    this.dashboardService.getTransactionsByAccountId(this.idAccount).subscribe(
      (response) => {
        console.log('soy la data de listado de transacciones: ', response);
        if (response.data && response.data.length > 0) {
          this.transacciones = Object.values(response.data);
          this.transacciones = response.data.sort((a: any, b: any) => {
            return (
              new Date(b.dateEmit).getTime() - new Date(a.dateEmit).getTime()
            );
          });
          console.log('Transacciones:', this.transacciones);
        } else {
          console.log('Información', 'No hay transacciones', 'info');
        }
      },
      (error) => {
        console.log(
          'Error',
          'No se pudieron obtener las transacciones',
          'error'
        );
      }
    );
  }
*/

  /*
  obtenerDatosCuentaPorAccountId(idAccount: string) {
    this.dashboardService.getAccountDataById(idAccount).subscribe(
      (response) => {
        console.log('Soy la data:', response);
        this.saldo = response.totalAccount;
        this.cvu = response.cvu;
        console.log('Soy el saldo:', this.saldo, 'soy el cvu:', this.cvu);
      },
      (error) => {
        console.error('Error al obtener los datos de la cuenta', error);
      }
    );
  }*/

  showCvu(): void {
    this.dashboardService.showCvu();
  }

  sortBy(key: string): void {
    if (this.sortKey === key) {
      this.orderBy = this.orderBy === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortKey = key;
      this.orderBy = 'asc';
    }
    this.transacciones = this.sortTable(
      this.transacciones,
      this.sortKey,
      this.orderBy
    );
  }

  sortTable(data: any[], key: string, order: string): any[] {
    return data.sort((a, b) => {
      const valueA = a[key];
      const valueB = b[key];
      if (order === 'asc') {
        return valueA < valueB ? -1 : valueA > valueB ? 1 : 0;
      } else {
        return valueA > valueB ? -1 : valueA < valueB ? 1 : 0;
      }
    });
  }
}
