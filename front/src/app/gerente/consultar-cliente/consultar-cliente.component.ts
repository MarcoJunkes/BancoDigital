import { Component, ViewChild } from '@angular/core';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { GerenteService } from '../services/gerente.service';
import { NgForm } from '@angular/forms';
import { Usuario } from 'src/app/shared';
@Component({
  selector: 'app-consultar-cliente',
  templateUrl: './consultar-cliente.component.html'
})
export class ConsultarClienteComponent {
  filtro = {
    cpf: ''
  }
  cliente!: Cliente;

  constructor(
    private gerenteService: GerenteService
  ){}

  pesquisarCliente(): void {
    this.gerenteService.buscarPorId(this.filtro.cpf)
      .subscribe(cliente => {
        this.gerenteService.abrirModalCliente(cliente);
      });
  }

  limparForm(): void {
    this.filtro = {
      cpf: ''
    };
  }
}
