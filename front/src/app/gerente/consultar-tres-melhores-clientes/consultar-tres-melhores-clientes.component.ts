import { Component, OnInit } from '@angular/core';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-consultar-tres-melhores-clientes',
  templateUrl: './consultar-tres-melhores-clientes.component.html'
})
export class ConsultarTresMelhoresClientesComponent implements OnInit {
  clientes: Cliente[] = [];
  constructor(private gerenteService : GerenteService){}
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
    this.gerenteService.abrirModalCliente(cliente)
  }
}
