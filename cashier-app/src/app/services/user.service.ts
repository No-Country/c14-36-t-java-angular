import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/User.interface';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly API = environment.apiLogin;
  private readonly API2 = environment.apiLogin;
  private readonly APIRegister = environment.apiRegister;
  private readonly http = inject(HttpClient);

  constructor() {}

  addNewUser(user: User): Observable<User> {
    return this.http.post<User>(this.APIRegister, user);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.API2);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.API}/${id}`);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.API}/${user.id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }

  loginUser(user: User) {
    const body = {
      email: user.email,
      password: user.password,
    };

    return this.http.post<any>(this.API, body);
  }
}
