import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/User.interface';
import { IGetAllUser, IGetUserRes, ILoginRes, IRegistRes } from '../interfaces/response.interface';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly APILOGIN = environment.apiLogin;
  private readonly APIGETUSER = environment.apiGetUser;
  private readonly APIGETALLUSER = environment.apiGetAllUser;
  private readonly APIRegister = environment.apiRegister;
  private readonly http = inject(HttpClient);

  constructor() {}

  addNewUser(user: User) {
    return this.http.post<IRegistRes>(this.APIRegister, user);
  }

  getAllUsers() {
    return this.http.get<IGetAllUser>(this.APIGETALLUSER);
  }

  getUser(userId: string) {
    return this.http.get<IGetUserRes>(this.APIGETUSER+userId);
  }
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.APILOGIN}/${user.id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.APILOGIN}/${id}`);
  }

  loginUser(user: User) {
    const body = {
      email: user.email,
      password: user.password,
    };

    return this.http.post<ILoginRes>(this.APILOGIN, body);
  }
}
