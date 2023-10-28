import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserData } from '../interfaces/userData.inteface';
@Injectable({
  providedIn: 'root',
})
export class TokenService {
  dataUser!:UserData;
  constructor() {
    window.addEventListener('storage', () => {
      this.tokenStatus.next(this.hasToken());
    });
  }

  private tokenStatus = new BehaviorSubject<boolean>(this.hasToken());

  public hasToken() {
    return localStorage.getItem('tokenCashier') !== null;
  }

  watchToken() {
    return this.tokenStatus.asObservable();
  }

  getTokenDecoded() {
    const token = localStorage.getItem('tokenCashier');
    if (token) {
      const helper = new JwtHelperService();
      let userData;
      try {
        userData = helper.decodeToken(token);
      } catch (error) {
        console.log("token invalido");
        userData = {invalid:true};
      }

      if(this.isValidToken(userData)){
        this.dataUser = userData;
        return userData;
      }
      return null;
    } else {
      return null;
    }
  }

  isValidToken(obj: any): obj is UserData {
    return (
      'iat' in obj &&
      'exp' in obj &&
      'iss' in obj &&
      'id' in obj &&
      'dni' in obj &&
      'sub' in obj &&
      'name' in obj &&
      'role' in obj
    );
  }
}
