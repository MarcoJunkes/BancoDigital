import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeClienteComponent } from './cliente/home-cliente/home-cliente.component';
import { AdminGerentesComponent } from './administrador';

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
    path: 'administrador/gerentes',
    component: AdminGerentesComponent
  }
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
