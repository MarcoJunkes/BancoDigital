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
  filtro = {
    cpf: '',
    nome: ''
  }
  clientes: Cliente[] = [];
  conta: Conta[] = [];

  constructor(private gerenteService : GerenteService){}

  ngOnInit(): void {
    this.clientes = [];
    this.listarTodos();
  }

  listarTodos(): Cliente[] {
    const filtro: any = {};
    if (this.filtro.cpf) {
      filtro['cpf'] = this.filtro.cpf.trim();
    }
    if (this.filtro.nome) {
      filtro['nome'] = this.filtro.nome.trim();
    }

    this.gerenteService.listarTodos(filtro).subscribe(({ clientes }) => this.clientes = clientes);
    return this.clientes;
  }
  
  limparFiltro() {
    this.filtro = {
      cpf: '',
      nome: ''
    }
    this.listarTodos();
  }

  abrirModalCliente(cliente: Cliente) {
    this.gerenteService.abrirModalCliente(cliente)
  }
}
