import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';
import { ModalClienteComponent } from './modal-cliente/modal-cliente.component';
import { GerenteService } from './services/gerente.service';
import { FormsModule } from '@angular/forms';
import { TelaInicialGerenteComponent } from './tela-inicial-gerente/tela-inicial-gerente.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    ContultarTodosClientesComponent,
    ModalClienteComponent,
    TelaInicialGerenteComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
  ],
  providers: [
    GerenteService,
    RouterModule,
  ]
})
export class GerenteModule {
}
