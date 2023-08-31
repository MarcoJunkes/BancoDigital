import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';
import { ModalClienteComponent } from './modal-cliente/modal-cliente.component';
// import { Router } from '@angular/router';


@NgModule({
  declarations: [
    ContultarTodosClientesComponent,
    ModalClienteComponent
  ],
  imports: [
    CommonModule
  ]
})
export class GerenteModule {
  // constructor(
  //   private router: Router
  // ) {}
}
