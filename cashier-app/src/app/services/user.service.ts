import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../interfaces/User.interface';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly API = environment.api;
  private readonly API2 = environment.api2;
  private readonly http = inject(HttpClient);

  constructor() { }

  addNewUser(user: User): Observable<User> {
    return this.http.post<User>(this.API, user)
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.API2);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.API}/${id}`);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.API}/${user.id}`, user)
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`)
  }
}
