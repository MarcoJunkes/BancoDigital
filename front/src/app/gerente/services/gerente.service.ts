import { Injectable } from '@angular/core';

import { Cliente } from 'src/app/shared/models/cliente.model';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const LS_CHAVE: string = "cliente";

@Injectable({
  providedIn: 'root'
})
export class GerenteService {
  BASE_URL = "http://localhost:3000/clientes/"; //Caso dê erro, tirar a última barra
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) { }

  listarTodos(): Observable<Cliente[]> {
    return this.httpClient.get<Cliente[]>(this.BASE_URL, this.httpOptions);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.httpClient.get<Cliente>(this.BASE_URL + id, this.httpOptions);
  }

  /* Modelo com localstorage
  inserir(cliente: Cliente): void {
    const clientes = this.listarTodos();
    cliente.id = new Date().getTime();
    clientes.push(cliente);
    localStorage[LS_CHAVE] = JSON.stringify(clientes);
  }

  buscarPorId(id: number): Cliente | undefined {
    const clientes: Cliente[] = this.listarTodos();
    return clientes.find(cliente => cliente.id === id);
  }

  atualizar(cliente: Cliente): void {
    const clientes: Cliente[] = this.listarTodos();
    clientes.forEach( (obj, index, objs) => {
      if(cliente.id === obj.id) {
        objs[index] = cliente
      }
    });
    localStorage[LS_CHAVE] = JSON.stringify(clientes);
  }

  remover(id: number): void {
    let clientes: Cliente[] = this.listarTodos();
    clientes = clientes.filter(cliente => cliente.id !== id);
    localStorage[LS_CHAVE] = JSON.stringify(clientes);
  }*/
}
