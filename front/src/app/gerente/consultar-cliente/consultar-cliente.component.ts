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
  clientes: Cliente[] = [];
  usuario!: Usuario;
  cliente!: Cliente;
  @ViewChild('formConsultarCliente') formConsultarCliente!: NgForm;

  constructor(
    private gerenteService : GerenteService
  ){}

  pesquisarCliente(): void{
    this.abrirModalCliente({id: 1, cpf: '123.456.789-12', nome: 'Tiago', cidade: 'Curitiba', estado: 'PR', gerente: 'Fulano', limite: 1000, salario: 1000, saldo: 2500});
  }

  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }

  limparForm() {
    this.formConsultarCliente.reset({});
  }
}
