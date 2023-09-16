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
    let usu = new Usuario('1', "Razer-Func", login.email, login.senha, '1111-1111', 'R. Abc' ,"cliente");
    if (login.email == login.senha) {
      if (login.email?.startsWith("admin")) {
        usu = new Usuario('1', "Razer-Admin", login.email, login.senha, '1111-1111', 'R. Abc', "admin");
      }
      else if (login.email?.startsWith("gerente")) {
        usu = new Usuario('1', "Razer-Gerente", login.email, login.senha, '1111-1111', 'R. Abc', "gerente");
      }
      return of(usu);
    }
    else {
      return of(null);
    }
  }
}