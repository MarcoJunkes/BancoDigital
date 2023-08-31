import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';
@Component({
  selector: 'app-contultar-todos-clientes',
  templateUrl: './contultar-todos-clientes.component.html'
})
export class ContultarTodosClientesComponent {
  constructor(
    private modalService: NgbModal
  ){}
  abrirModalCliente() {
    const modalRef = this.modalService.open(ModalClienteComponent);
    // modalRef.componentInstance.cliente = cliente;
  }
}
