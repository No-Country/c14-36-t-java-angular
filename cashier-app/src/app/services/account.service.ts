import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { IGetAccountRes, IcardRes } from '../interfaces/response.interface';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private readonly APIACCOUNT = environment.apiGetAccount;
  private readonly APICARD = environment.apiGetCard;

  constructor(private http:HttpClient) { }

  getAccount(accountId:string){
    return this.http.get<IGetAccountRes>(this.APIACCOUNT+accountId)
  }
  getCard(cardId:string){
    return this.http.get<IcardRes>(this.APICARD+cardId)
  }
}
