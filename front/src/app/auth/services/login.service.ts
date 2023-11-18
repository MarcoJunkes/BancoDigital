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

  /*public logout(): Observable<unknown> {
    return this.http.post(`${environment.api}/logout`, null, this.httpOptions);
  }*/

/*
  login(login: Login): Observable<Usuario | null> {
    let usu = new Usuario('1', "Razer-Func", login.email, login.senha, '1111-1111', 'R. Abc' ,"cliente");
    if (login.email == login.senha) {
      if (login.email?.startsWith("admin")) {
        usu = new Usuario('1', "Razer-Admin", login.email, login.senha, '1111-1111', 'R. Abc', "admin");
      }
      else if (login.email?.startsWith("gerente")) {
        usu = new Usuario('1', "Razer-Gerente", login.email, login.senha, '1111-1111', 'R. Abc', "gerente");
      }
      console.log(usu);
      return of(usu);
    }
    else {
      return of(null);
    }
  }*/

  public getAuthorizationToken() {
    const token = localStorage.getItem(LS_CHAVE_TOKEN);
    console.log('getToken: ', token);
    return token;
  }

  public setAuthorizationToken(token: string) {
    localStorage.setItem(LS_CHAVE_TOKEN, token);
  }

  /* public getTokenExpirationDate(token: string): Date {
    const decoded: any = jwtDecode(token);

    const date = new Date(0);
    if (decoded.exp === undefined) {
      return date;
    }

    date.setUTCSeconds(decoded.exp);
    return date;
  } */

  /* public isTokenExpired(token?: string): boolean {
    if (!token) {
      return true;
    }

    const date = this.getTokenExpirationDate(token);
    if (date === new Date(0)) {
      return false;
    }

    return date.valueOf() < new Date().valueOf();
  }

  public isUserLoggedIn() {
    const token = this.getAuthorizationToken();
    if (!token) {
      return false;
    }

    if (this.isTokenExpired(token)) {
      return false;
    }

    return true;
  }
*/

  /* Antigo */
  logout() {
    delete localStorage[LS_CHAVE];
  }
/*
  login(login: Login): Observable<Usuario | null> {
    let usu = new Usuario('1', "Razer-Func", login.email, login.senha, '1111-1111', 'R. Abc' ,"cliente");
    if (login.email == login.senha) {
      if (login.email?.startsWith("admin")) {
        usu = new Usuario('1', "Razer-Admin", login.email, login.senha, '1111-1111', 'R. Abc', "admin");
      }
      else if (login.email?.startsWith("gerente")) {
        usu = new Usuario('1', "Razer-Gerente", login.email, login.senha, '1111-1111', 'R. Abc', "gerente");
      }
      console.log(usu);
      return of(usu);
    }
    else {
      return of(null);
    }
  }*/

}