import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { AdministradorService } from '../services/administrador.service';

@Component({
  selector: 'app-consultar-clientes-adm',
  templateUrl: './consultar-clientes-adm.component.html'
})
export class ConsultarClientesAdmComponent implements OnInit {

  clientes: Cliente[] =[];

  constructor(private adminService: AdministradorService) {}

  ngOnInit(): void {
    this.listarTodosClientes();
  }

  /*Dinâmico
  listarTodosClientes() {
    this.adminService.listarTodosClientes()
      .subscribe((clientes: Cliente[]) => {
        this.clientes = clientes;
      });
  } */
  /*Estático */
  listarTodosClientes() {
    this.clientes = [
      {
        "id": 1,
        "nome": "Cliente 1",
        "cpf": "12345678912",
        "cidade": "Curitiba",
        "estado": "Paraná",
        "saldo": 15000,
        "limite": 50000,
        "gerente": "Gerente 2",
        "salario": 5000 
      },
      {
        "id": 2,
        "nome": "Cliente 2",
        "cpf": "12345678913",
        "cidade": "Curitiba",
        "estado": "Paraná",
        "saldo": 7000,
        "limite": 1500,
        "gerente": "Gerente 1",
        "salario": 3000 
      }
    ]
  }
}
