import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ITransactionDTO } from '../interfaces/transaction.interface';

@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private readonly APITRANSFER = environment.apiNewTransfer;
  constructor(private http:HttpClient){}

  newTransfer(idAccount:string, data:ITransactionDTO){
    const params = new HttpParams().set('idAccount',idAccount);
    return this.http.post<any>(this.APITRANSFER,data,{params})
  }
}
