import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { ITransactionDTO } from '../interfaces/transaction.interface';
import { ItransactionRes } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private readonly APITRANSFER = environment.apiNewTransfer;
  constructor(private http:HttpClient){}

  newTransfer(data:ITransactionDTO){
    return this.http.post<ItransactionRes>(this.APITRANSFER,data);
  }
}
