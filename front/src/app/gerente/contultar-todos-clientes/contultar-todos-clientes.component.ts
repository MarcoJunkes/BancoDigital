import { Component, OnInit, ViewChild } from '@angular/core';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { NgForm } from '@angular/forms';
import { Conta, Usuario } from 'src/app/shared';

@Component({
  selector: 'app-contultar-todos-clientes',
  templateUrl: './contultar-todos-clientes.component.html'
})

export class ContultarTodosClientesComponent implements OnInit {

  clientes: Cliente[] = [];
  usuarios: Usuario[] = [];
  conta: Conta[] = [];

  @ViewChild('formCpf') formCpf!: NgForm;

  constructor(private gerenteService : GerenteService){}

  ngOnInit(): void {
    this.clientes = [];
    this.usuarios = [];
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
          this.listarContas(this.clientes);
        }
      }
    });
    return this.clientes;
  }

  listarContas(cliente: Usuario[]){
    this.gerenteService.listarTodos();
  }
  
  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }

  limparForm() {
    this.formCpf.reset({});
  }
}
