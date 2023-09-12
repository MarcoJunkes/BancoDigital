import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminGerentesComponent } from './admin-gerentes/admin-gerentes.component';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdministradorService } from './services/administrador.service';
import { ListarGerenteComponent } from './listar-gerente/listar-gerente.component';
import { InserirGerenteComponent } from './inserir-gerente/inserir-gerente.component';
import { EditarGerenteComponent } from './editar-gerente/editar-gerente.component';
import { HomeAdmComponent } from './home-adm/home-adm.component';

@NgModule({
  declarations: [
    AdminGerentesComponent,
    ListarGerenteComponent,
    InserirGerenteComponent,
    EditarGerenteComponent,
    HomeAdmComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    AdministradorService
  ]
})
export class AdministradorModule { }
