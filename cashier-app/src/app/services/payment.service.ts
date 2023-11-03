import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { IBillHistoryRes, IBillRes, IGetPayment } from '../interfaces/response.interface';
import { IBillDTO } from '../interfaces/transaction.interface';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private readonly APIGETALLPAYMENT = environment.apiGetAllPayments;
  private readonly APINEWPAYMENT = environment.apiNewPayment;
  private readonly APIGETBILL = environment.apiGetPayment;
  constructor(
    private http:HttpClient
  ) { }
  getAllPayments(){
    return this.http.get<IGetPayment[]>(this.APIGETALLPAYMENT)
  }

  newPayment(dataBill:IBillDTO){
    return this.http.post<IBillRes>(this.APINEWPAYMENT,dataBill);
  }

  getBill(idAccount:string, page:number=0, size:number=10, order:number=0, field:string='dateEmit'){
    const params = new HttpParams()
      .set('bill_type','servicio')
      .set('idAccount',idAccount)
      .set('page',page)
      .set('size',size)
      .set('order',order)
      .set('field',field)
    return this.http.get<IBillHistoryRes>(this.APIGETBILL, {params})
  }
}
