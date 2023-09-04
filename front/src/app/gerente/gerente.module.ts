import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';
import { ModalClienteComponent } from './modal-cliente/modal-cliente.component';
import { TelaInicialGerenteComponent } from './tela-inicial-gerente/tela-inicial-gerente.component';
import { RouterModule } from '@angular/router';
import { ConsultarClienteComponent } from './consultar-cliente/consultar-cliente.component';
import { ModalTelaInicialComponent } from './modal-tela-inicial/modal-tela-inicial.component';

@NgModule({
  declarations: [
    ContultarTodosClientesComponent,
    ModalClienteComponent,
    TelaInicialGerenteComponent,
    ConsultarClienteComponent,
    ModalTelaInicialComponent,
  ],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class GerenteModule {
}
