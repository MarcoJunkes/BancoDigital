import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminGerentesComponent } from './admin-gerentes/admin-gerentes.component';

import { RouterModule } from '@angular/router';
import { GerenteService } from './services/administrador.service';

@NgModule({
  declarations: [
    AdminGerentesComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  providers: [
    GerenteService
  ]
})
export class AdministradorModule { }
