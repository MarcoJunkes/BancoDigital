import { Injectable } from '@angular/core';
import { Login, Usuario } from '../../shared';
import { Observable, of } from 'rxjs';

const LS_CHAVE: string = "usuarioLogado";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public get usuarioLogado(): Usuario {
    let usu = localStorage[LS_CHAVE];
    return (usu ? JSON.parse(localStorage[LS_CHAVE]) : null);
  }
  
  public set usuarioLogado(usuario: Usuario) {
    localStorage[LS_CHAVE] = JSON.stringify(usuario);
  }
  
  logout() {
    delete localStorage[LS_CHAVE];
  }

  login(login: Login): Observable<Usuario | null> {
    let usu = new Usuario(1, "Razer-Func", login.email, login.senha, "FUNC");
    if (login.email == login.senha) {
      if (login.email == "admin") {
        usu = new Usuario(1, "Razer-Admin",
        login.email, login.senha, "ADMIN");
      }
      else if (login.email == "gerente") {
        usu = new Usuario(1, "Razer-Gerente",
        login.email, login.senha, "GERENTE");
      }
      return of(usu);
    }
    else {
      return of(null);
    }
  }
}