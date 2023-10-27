import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ICard } from '../interfaces/account.interface';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private readonly APIACCOUNT = environment.apiGetAccount;
  private readonly APICARD = environment.apiGetCard;

  constructor(private http:HttpClient) { }

  getAccount(accountId:string){
    return this.http.get<any>(this.APIACCOUNT+accountId)
  }
  getCard(cardId:string){
    return this.http.get<ICard>(this.APICARD+cardId)
  }
}
