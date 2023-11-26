import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from 'src/app/shared';
import { environment } from 'src/environments/environment';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Injectable({
  providedIn: 'root'
})
export class GerenteService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient,
              private modalService: NgbModal) { }

  listarTodos(): Observable<any[]> {
    let gerenteCpf = JSON.parse(localStorage.getItem('usuarioLogado') || '')['cpf'];
    return this.httpClient.get<any>(`${environment.api}/clientes`, this.httpOptions);
  }

  listarTop3(): Observable<{clientes: Cliente[]}> {
    let gerenteCpf = JSON.parse(localStorage.getItem('usuarioLogado') || '')['cpf'];
    return this.httpClient.get<any>(`${environment.api}/clientes/top3?gerenteCpf=${gerenteCpf}`, this.httpOptions);
  }

  buscarConta(usuario: Usuario): Observable<any[]> {
    return this.httpClient.get<any>(`${environment.api}/contas/${usuario.cpf}`, this.httpOptions);
  } 

  buscarPorId(id: number): Observable<Usuario> {
    return this.httpClient.get<Usuario>(`${environment.api}/clientes/${id}`, this.httpOptions);
  }

  abrirModalCliente(cliente: Usuario) {
    const modalRef = this.modalService.open(ModalClienteComponent);
    modalRef.componentInstance.cliente = cliente;
  }

  aprovarCliente(usuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(`${environment.api}/aprovarConta/${usuario.cpf}`, this.httpOptions);
  }
  rejeitarCliente(usuario: Usuario): Observable<any> {
    return this.httpClient.post<any>(`${environment.api}/clientes/${usuario.cpf}/rejeitar`, this.httpOptions);
  }
}
