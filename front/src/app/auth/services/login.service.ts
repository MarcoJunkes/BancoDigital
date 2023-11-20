import { Injectable } from '@angular/core';
import { Login, Usuario } from '../../shared';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import jwtDecode from 'jwt-decode';
import { environment } from 'src/environments/environment';

const LS_CHAVE: string = "usuarioLogado";
const LS_CHAVE_TOKEN: string = "token";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  
  constructor(private http: HttpClient){}

  public get usuarioLogado(): Usuario {
    let usu = localStorage[LS_CHAVE];
    // console.log(usu.perfil);
    return (usu ? JSON.parse(localStorage[LS_CHAVE]) : null);
  }
  
  public set usuarioLogado(usuario: Usuario) {
    localStorage[LS_CHAVE] = JSON.stringify(usuario);
  }

  public login(login: Login): Observable<any> {
    return this.http.post<any>(`${environment.api}/login`, login, this.httpOptions);
  }

  public getAuthorizationToken() {
    const token = localStorage.getItem(LS_CHAVE_TOKEN);
    // console.log('getToken: ', token);
    return token;
  }

  public setAuthorizationToken(token: string) {
    localStorage.setItem(LS_CHAVE_TOKEN, token);
  }

  logout() {
    delete localStorage[LS_CHAVE];
    delete localStorage[LS_CHAVE_TOKEN];
  }
}