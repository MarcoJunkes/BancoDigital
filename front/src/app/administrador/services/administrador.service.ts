import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { environment } from 'src/environments/environment';


const LS_CHAVE: string = "admin";

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient,
  ) { }
  
  public listarTodosGerentes(): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes`, this.httpOptions);
  }

  public criarGerente(gerente: Gerente): Observable<any> {
    return this.http.post<any>(`${environment.api}/gerentes`, gerente, this.httpOptions);
  }

  public buscarGerentePorId(id: string): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);
  }

  public atualizarGerente(gerente: Gerente): Observable<any> {
    return this.http.put<any>(`${environment.api}/gerentes/${gerente.id}`, gerente, this.httpOptions);
  }

  public removerGerente(id: string): Observable<any> {
    return this.http.delete<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);
  }
/* Antigo
  listarTodos(): Gerente[]{
    const gerentes = localStorage[LS_CHAVE];
    return gerentes ? JSON.parse(gerentes) : [];
  }

  inserir(gerente: Gerente): void{
    const gerentes = this.listarTodos();
    gerente.id = new Date().getTime();
    gerentes.push(gerente);
    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  }
  buscarPorId(id : number): Gerente | undefined{
    const gerentes: Gerente[] = this.listarTodos();

    return gerentes.find(gerente => gerente.id === id);
  }
  atualizar(gerente: Gerente): void{
    const gerentes = this.listarTodos();

    gerentes.forEach(
      (obj,index,objs) => {
        if(gerente.id === obj.id){
        objs[index] = gerente;
        }
      }
    );

    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  }
  remover(id: number): void{
    let gerentes: Gerente[] = this.listarTodos();

    gerentes = gerentes.filter( gerente => gerente.id !== id)
    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  } */
}
