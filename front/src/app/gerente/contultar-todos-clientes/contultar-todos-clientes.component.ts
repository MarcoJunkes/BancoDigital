import { Component, OnInit, ViewChild } from '@angular/core';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-contultar-todos-clientes',
  templateUrl: './contultar-todos-clientes.component.html'
})

export class ContultarTodosClientesComponent implements OnInit {

  clientes: Cliente[] = [];

  @ViewChild('formCpf') formCpf!: NgForm;

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

  limparForm() {
    console.log('Ok')
    this.formCpf.reset({});
  }
}
