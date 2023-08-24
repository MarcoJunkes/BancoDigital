import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';



@NgModule({
  declarations: [
    ContultarTodosClientesComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ContultarTodosClientesComponent
  ],
})
export class GerenteModule { }
