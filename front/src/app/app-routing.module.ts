import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeClienteComponent } from './cliente/home-cliente/home-cliente.component';
import { ListarGerenteComponent } from './administrador/listar-gerente/listar-gerente.component'; 
import { LoginRoutes } from './auth/auth-routing.module';
import { AlterarPerfilComponent } from './cliente/alterar-perfil/alterar-perfil.component';
import { InserirGerenteComponent } from './administrador/inserir-gerente/inserir-gerente.component';
import { EditarGerenteComponent } from './administrador/editar-gerente/editar-gerente.component';

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
