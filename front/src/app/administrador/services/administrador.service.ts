import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/auth/services/login.service';
import { Conta } from 'src/app/shared';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {
  
  constructor(
    private http: HttpClient,
    private loginService: LoginService
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  
  public listarTodosGerentes(): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes`, this.httpOptions);
  }

  public listarTodosGerentes2(): Observable<any> {
    return this.http.get<any>(`${environment.api}/contas/gerentes`, this.httpOptions);
  }

  public criarGerente(gerente: Gerente): Observable<any> {
    return this.http.post<any>(`${environment.api}/gerentes/inserir`, gerente, this.httpOptions);
  }

  public buscarGerentePorId(id: number): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);
  }

  public atualizarGerente(gerente: Gerente): Observable<any> {
    return this.http.put<any>(`${environment.api}/gerentes/${gerente.id}`, gerente, this.httpOptions);
  }

  public removerGerente(id: number): Observable<any> {
    // return this.http.delete<any>(`${environment.api}/removerGerentes/${id}`, this.httpOptions);
    return this.http.delete<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);

  }

  public listarTodosClientes(): Observable<{clientes: Cliente[]}> {
    return this.http.get<any>(`${environment.api}/clientes`, this.httpOptions);
  }
}
