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
  usuarios: Usuario[] = [];
  usuario!: Usuario;
  data!: Cliente;
  cliente!: Cliente;
  @ViewChild('formConsultarCliente') formConsultarCliente!: NgForm;

  constructor(
    private gerenteService : GerenteService
  ){}

  pesquisarCliente(): void{
    if (this.formConsultarCliente.form.valid) {
      this.gerenteService.abrirModalCliente(this.usuario);
    }
  }

  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }

  limparForm() {
    this.formConsultarCliente.reset({});
  }
}
