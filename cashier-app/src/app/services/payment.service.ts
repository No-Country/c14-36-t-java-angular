import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IBillRes, IGetPayment } from '../interfaces/response.interface';
import { IBillDTO } from '../interfaces/transaction.interface';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private readonly APIGETALLPAYMENT = environment.apiGetAllPayments;
  private readonly APINEWPAYMENT = environment.apiNewPayment;
  constructor(
    private http:HttpClient
  ) { }
  getAllPayments(){
    return this.http.get<IGetPayment[]>(this.APIGETALLPAYMENT)
  }
  newPayment(dataBill:IBillDTO){
    return this.http.post<IBillRes>(this.APINEWPAYMENT,dataBill);
  }
}
