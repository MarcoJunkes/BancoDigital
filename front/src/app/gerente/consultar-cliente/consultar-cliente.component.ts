import { Component } from '@angular/core';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { GerenteService } from '../services/gerente.service';

@Component({
  selector: 'app-consultar-cliente',
  templateUrl: './consultar-cliente.component.html'
})
export class ConsultarClienteComponent {
  clientes: Cliente[] = [];

  constructor(
    private gerenteService : GerenteService
  ){}

  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }
}
