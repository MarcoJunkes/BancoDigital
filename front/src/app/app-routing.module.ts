import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeClienteComponent } from './cliente/home-cliente/home-cliente.component';
import { AdminGerentesComponent } from './administrador';
import { LoginRoutes } from './auth/auth-routing.module';
import { AlterarPerfilComponent } from './cliente/alterar-perfil/alterar-perfil.component';
import { DepositoComponent } from './cliente/deposito/deposito.component';
import { SaqueComponent } from './cliente/saque/saque.component';
import { ContultarTodosClientesComponent } from './gerente/contultar-todos-clientes/contultar-todos-clientes.component';
import { ConsultaExtratoComponent } from './cliente/consulta-extrato/consulta-extrato.component';
import { TransferenciaComponent } from './cliente/transferencia/transferencia.component';
import { TelaInicialGerenteComponent } from './gerente/tela-inicial-gerente/tela-inicial-gerente.component';

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
    path: 'gerente/consultar-todos',
    component: ContultarTodosClientesComponent,
  },
  {
    path: 'cliente/consulta-extrato',
    component: ConsultaExtratoComponent,
  },
  {
    path: 'administrador/gerentes',
    component: AdminGerentesComponent
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
