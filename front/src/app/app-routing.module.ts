import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeClienteComponent } from './cliente/home-cliente/home-cliente.component';
import { ListarGerenteComponent } from './administrador/listar-gerente/listar-gerente.component'; 
import { LoginRoutes } from './auth/auth-routing.module';
import { AlterarPerfilComponent } from './cliente/alterar-perfil/alterar-perfil.component';
import { InserirGerenteComponent } from './administrador/inserir-gerente/inserir-gerente.component';
import { EditarGerenteComponent } from './administrador/editar-gerente/editar-gerente.component';
import { DepositoComponent } from './cliente/deposito/deposito.component';
import { SaqueComponent } from './cliente/saque/saque.component';
import { ContultarTodosClientesComponent } from './gerente/contultar-todos-clientes/contultar-todos-clientes.component';
import { ModalClienteComponent } from './gerente/modal-cliente/modal-cliente.component';
import { ConsultaExtratoComponent } from './cliente/consulta-extrato/consulta-extrato.component';
import { TransferenciaComponent } from './cliente/transferencia/transferencia.component';
import { TelaInicialGerenteComponent } from './gerente/tela-inicial-gerente/tela-inicial-gerente.component';
import { ConsultarClienteComponent } from './gerente/consultar-cliente/consultar-cliente.component';
import { HomeAdmComponent } from './administrador/home-adm/home-adm.component';
import { ConsultarClientesAdmComponent } from './administrador/consultar-clientes-adm/consultar-clientes-adm.component';
import { ConsultarTresMelhoresClientesComponent } from './gerente/consultar-tres-melhores-clientes/consultar-tres-melhores-clientes.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'cliente/home',
    pathMatch: 'full',
  },
  {
    path: 'cliente/home',
    component: HomeClienteComponent
  },
  {
    path: 'cliente/alterar-perfil',
    component: AlterarPerfilComponent
  },
  {
    path: 'cliente/depositar',
    component: DepositoComponent
  },
  {
    path: 'cliente/transferencia',
    component: TransferenciaComponent
  },
  {
    path: 'cliente/sacar',
    component: SaqueComponent
  },
  {
    path: 'gerente/tela-inicial-gerente',
    component: TelaInicialGerenteComponent
  },
  {
    path: 'gerente/consultar-cliente',
    component: ConsultarClienteComponent
  },
  {
    path: 'gerente/consultar-todos',
    component: ContultarTodosClientesComponent,
  },
  {
    path: 'gerente/consultar-tres-melhores',
    component: ConsultarTresMelhoresClientesComponent,
  },
  {
    path: 'cliente/consulta-extrato',
    component: ConsultaExtratoComponent,
  },
  {
    path: 'gerente/modal-cliente', // Depois precisa inserir por id
    component: ModalClienteComponent
  },
  {
    path: 'administrador/gerentes',
    component: ListarGerenteComponent
  },
  {
    path: 'administrador/gerentes/novo',
    component: InserirGerenteComponent
  },
  {
    path: 'administrador/gerentes/editar/:id',
    component: EditarGerenteComponent
  },
  {
    path: 'administrador',
    component: HomeAdmComponent
  },
  {
    path: 'administrador/clientes',
    component: ConsultarClientesAdmComponent
  },
  ...LoginRoutes
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
