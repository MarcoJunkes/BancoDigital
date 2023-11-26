import { Component, OnInit } from '@angular/core';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-consultar-tres-melhores-clientes',
  templateUrl: './consultar-tres-melhores-clientes.component.html'
})
export class ConsultarTresMelhoresClientesComponent implements OnInit {
  public clientes: Cliente[] = [];

  constructor(private gerenteService : GerenteService){}

  ngOnInit(): void {
    this.listarTodos();
  }

  listarTodos(): void {
    this.gerenteService.listarTop3().subscribe(({clientes}) => this.clientes = clientes);
  }
  
  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }
}
