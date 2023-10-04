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
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'cliente/home',
    component: HomeClienteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'cliente/alterar-perfil',
    component: AlterarPerfilComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'cliente/depositar',
    component: DepositoComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'cliente/transferencia',
    component: TransferenciaComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'cliente/sacar',
    component: SaqueComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'cliente/consulta-extrato',
    component: ConsultaExtratoComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['cliente']
    }
  },
  {
    path: 'gerente/tela-inicial-gerente',
    component: TelaInicialGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'gerente/consultar-cliente',
    component: ConsultarClienteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'gerente/consultar-todos',
    component: ContultarTodosClientesComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'gerente/consultar-tres-melhores',
    component: ConsultarTresMelhoresClientesComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'cliente/consulta-extrato',
    component: ConsultaExtratoComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'gerente/modal-cliente', // Depois precisa inserir por id
    component: ModalClienteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gerente']
    }
  },
  {
    path: 'administrador/gerentes',
    component: ListarGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
  },
  {
    path: 'administrador/gerentes/novo',
    component: InserirGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
  },
  {
    path: 'administrador/gerentes/editar/:id',
    component: EditarGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
  },
  {
    path: 'administrador',
    component: HomeAdmComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
  },
  {
    path: 'administrador/clientes',
    component: ConsultarClientesAdmComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
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
