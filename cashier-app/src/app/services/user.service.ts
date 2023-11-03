import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { User } from '../interfaces/User.interface';
import { IFilterUserRes, IGetAllUserRes, IGetUserRes, ILoginRes, IRegistRes } from '../interfaces/response.interface';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly APILOGIN = environment.apiLogin;
  private readonly APIGETUSER = environment.apiGetUser;
  private readonly APIGETALLUSER = environment.apiGetAllUser;
  private readonly APIRegister = environment.apiRegister;
  private readonly http = inject(HttpClient);
  private readonly APIFILTERUSER = environment.apiFilterUser;

  constructor() {}

  addNewUser(user: User) {
    return this.http.post<IRegistRes>(this.APIRegister, user);
  }

  getAllUsers(page=0, size=4, order=0, field='id') {
    const params = {page,size,order,field}
    return this.http.get<IGetAllUserRes>(this.APIGETALLUSER, {params});
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

  filterUser(userData:string, page:number){
    const params = new HttpParams().set('size', 10).set('page', page);
    return this.http.get<IFilterUserRes>(this.APIFILTERUSER+userData,{params})
  }
}
