import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';

@Component({
  selector: 'app-consultar-cliente',
  templateUrl: './consultar-cliente.component.html'
})
export class ConsultarClienteComponent {
  constructor(
    private modalService: NgbModal
  ){}
  abrirModalCliente() {
    const modalRef = this.modalService.open(ModalClienteComponent);
    // modalRef.componentInstance.cliente = cliente;
  }
}
