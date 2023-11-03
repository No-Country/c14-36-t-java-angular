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
    this.isLoading = true;
    const userDataDecoded = this.tokenService.getTokenDecoded();
    const idUser = userDataDecoded.id;
    console.log('idUser: ', idUser);
    console.log('token Decoded: ', userDataDecoded);

    const obtenerDatos = async () => {
      try {
        await this.obtenerDatosPorUUID(idUser);
        await this.obtenerTransaccionesPorAccountId(); //
        await this.obtenerDatosCuentaPorAccountId(this.idAccount);
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
        if (response.data.content && response.data.content.length > 0) {
          this.transacciones = Object.values(response.data.content);
          this.transacciones = response.data.content.sort((a: any, b: any) => {
            return (
              new Date(b.dateEmit).getTime() - new Date(a.dateEmit).getTime()
            );
          });
          resolve();
        } else {
          console.log('Información', 'No hay transacciones', 'info');
          resolve();
          //reject('No hay transacciones');
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
      console.log("ObtT: ", this.idAccount)
      try {
        const response = await this.dashboardService.getAccountDataById(
          idAccount
        );
        console.log('Data de la cuenta:', response);
        this.saldo = response.data.totalAccount;
        console.log('Saldo: ', this.saldo);
        this.cvu = response.data.cvu;
        this.showcvu = true;
        console.log("Acabé obtenerDatosCuentaPorAccountId");

        resolve();
      } catch (error) {
        console.error('Error al obtener los datos de la cuenta', error);
        reject(error);
      }
    });
  }

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
