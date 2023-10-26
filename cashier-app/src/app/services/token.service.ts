import { Injectable} from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() {
    window.addEventListener('storage', () => {
      this.tokenStatus.next(this.hasToken())
    })
  }

  private tokenStatus = new BehaviorSubject<boolean>(this.hasToken());

  public hasToken() {
    return localStorage.getItem('tokenCashier') !== null;
  }

  watchToken() {
    return this.tokenStatus.asObservable();
  }

  getTokenDecoded(){
    const token = localStorage.getItem('tokenCashier');
    if (token) {
      const helper = new JwtHelperService();
      return helper.decodeToken(token);
    } else {
      return null;
    }
  }
}
