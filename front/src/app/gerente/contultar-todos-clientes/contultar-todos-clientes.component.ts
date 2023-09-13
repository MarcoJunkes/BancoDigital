import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalClienteComponent } from '../modal-cliente/modal-cliente.component';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-contultar-todos-clientes',
  templateUrl: './contultar-todos-clientes.component.html'
})

export class ContultarTodosClientesComponent implements OnInit {

  clientes: Cliente[] = [];

  constructor(private gerenteService : GerenteService,
              private modalService: NgbModal){}

  ngOnInit(): void {
    this.clientes = [];
    this.listarTodos();
  }

  listarTodos(): Cliente[] {
    this.gerenteService.listarTodos().subscribe({
      next: (data: Cliente[]) => {
        if (data == null) {
          this.clientes = [];
        }
        else {
          this.clientes = data;
        }
      }
    });
    return this.clientes;
  }
  
  abrirModalCliente(cliente: Cliente) {
    const modalRef = this.modalService.open(ModalClienteComponent);
    modalRef.componentInstance.cliente = cliente;
  }
}
