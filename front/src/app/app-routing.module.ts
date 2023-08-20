import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeClienteComponent } from './cliente/home-cliente/home-cliente.component';
import { AdminGerentesComponent } from './administrador';
import { LoginRoutes } from './auth/auth-routing.module';
import { AlterarPerfilComponent } from './cliente/alterar-perfil/alterar-perfil.component';

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
