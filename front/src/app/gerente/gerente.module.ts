import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';
import { TelaInicialGerenteComponent } from './tela-inicial-gerente/tela-inicial-gerente.component';



@NgModule({
  declarations: [
    ContultarTodosClientesComponent,
    TelaInicialGerenteComponent
  ],
  imports: [
    CommonModule
  ]
})
export class GerenteModule { }
