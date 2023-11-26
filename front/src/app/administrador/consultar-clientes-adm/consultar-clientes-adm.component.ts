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

  listarTodosClientes() {
    this.adminService.listarTodosClientes()
      .subscribe((clientes) => {
        this.clientes = clientes.clientes;
      });
  } 
}
