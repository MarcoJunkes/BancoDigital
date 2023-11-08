import { Injectable } from '@angular/core';

import { Cliente } from 'src/app/shared/models/cliente.model';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

// const LS_CHAVE: string = "cliente";

@Injectable({
  providedIn: 'root'
})
export class GerenteService {
  BASE_URL = "http://localhost:3000/clientes"; //Api Gateway
  // BASE_URL = "http://localhost:5000/clientes"; // Direto com o MS
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient,
              private modalService: NgbModal) { }

  listarTodos(): Observable<Cliente[]> {
    return this.httpClient.get<Cliente[]>(this.BASE_URL, this.httpOptions);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.httpClient.get<Cliente>(this.BASE_URL + id, this.httpOptions);
  }

  abrirModalCliente(cliente: Cliente) {
    const modalRef = this.modalService.open(ModalClienteComponent);
    modalRef.componentInstance.cliente = cliente;
  }

}
