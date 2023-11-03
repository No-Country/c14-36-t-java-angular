import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class DashboardService{
  myAppUrl: string;
  myApiCustomers: string;
  myApiTransactions: string;
  myApiAccounts: string;
  myApiPayments:string;

  constructor(private http: HttpClient) {
    this.myAppUrl = 'https://181.15.143.132:9698/v1/api';
    this.myApiCustomers = '/customers';
    this.myApiTransactions = '/transactions'
    this.myApiAccounts = '/accounts'
    this.myApiPayments = '/bills'
  }

  //PETICIONES
  getUserDataById(id: any): Observable<any> {
    return this.http.get<any>(`${this.myAppUrl}${this.myApiCustomers}/${id}`);
  }

  //COMO PROMESAS
  getTransactionsByAccountId(idAccount: string, page: number = 0, size: number = 4, order: number = 0, field: string = 'id'): Promise<any> {
    const params = `?idAccount=${idAccount}&page=${page}&size=${size}&order=${order}&field=${field}`;
    return (this.http.get<any>(`${this.myAppUrl}${this.myApiCustomers}${this.myApiTransactions}${params}`)).toPromise();
  }
  getAllTransactionsWhereDestiniIsMyId(){};
  //PARA RECUPERAR LOS PAGOS DE FACTURAS
  getPaymentsByAccountId(idAccount: string, page: number = 0, size: number = 4, order: number = 0, field: string = 'dateEmit'): Promise<any> {
    const params = `?idAccount=${idAccount}&page=${page}&size=${size}&order=${order}&field=${field}`;
    return this.http.get<any>(`${this.myAppUrl}${this.myApiCustomers}${this.myApiTransactions}${this.myApiPayments}${params}`).toPromise();
  }
  getAccountDataById(idAccount: string): Promise<any> {
    return this.http.get<any>(`${this.myAppUrl}${this.myApiAccounts}/${idAccount}`).toPromise();
  }


  //COMO OBSERVABLES
/*getTransactionsByAccountId(idAccount: string, page: number = 0, size: number = 4, order: number = 1, field: string = 'id'): Observable<any> {
    const params = `?idAccount=${idAccount}&page=${page}&size=${size}&order=${order}&field=${field}`;
    return this.http.get<any>(`${this.myAppUrl}${this.myApiCustomers}${this.myApiTransactions}${params}`);
  }*/

  /*
  getAccountDataById(idAccount: string): Observable<any> {
    return this.http.get<any>(`${this.myAppUrl}${this.myApiAccounts}/${idAccount}`);
  }*/


  //MÉTODOS
  showCvu() {
    const cvuData = document.getElementById("cvuData");
    const copyButton = document.getElementById("copyButton");

    if (!cvuData || !copyButton) {
        console.log("Elementos no encontrados en el DOM.");
        return;
    }

    if (cvuData.style.display === "none") {
        cvuData.style.display = "inline"; // Muestra el CVU
        copyButton.style.display = "inline"; // Muestra el botón de copiar

        // Copiar al portapapeles cuando se hace clic en el botón
        copyButton.addEventListener("click", () => {
            const cvuText = cvuData.innerText;
            const textArea = document.createElement("textarea");
            textArea.value = cvuText;
            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand("copy");
            document.body.removeChild(textArea);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Cvu copiado',
              showConfirmButton: false,
              timer: 1500,
              background: '#153230',
              color: '#fff'
            })
            cvuData.style.display = "none"; // Oculta el CVU
            copyButton.style.display = "none"; // Oculta el botón de copiar
        });

        // Ocultar el CVU y el botón después de 10 segundos
        setTimeout(() => {
            cvuData.style.display = "none";
            copyButton.style.display = "none";
        }, 10000); // 10000 milisegundos = 10 segundos
    }
}

}
