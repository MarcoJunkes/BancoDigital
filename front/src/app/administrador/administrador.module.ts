import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdministradorService } from './services/administrador.service';
import { ListarGerenteComponent } from './listar-gerente/listar-gerente.component';
import { InserirGerenteComponent } from './inserir-gerente/inserir-gerente.component';
import { EditarGerenteComponent } from './editar-gerente/editar-gerente.component';
import { HomeAdmComponent } from './home-adm/home-adm.component';
import { ConsultarClientesAdmComponent } from './consultar-clientes-adm/consultar-clientes-adm.component';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';

@NgModule({
  declarations: [
    ListarGerenteComponent,
    InserirGerenteComponent,
    EditarGerenteComponent,
    HomeAdmComponent,
    ConsultarClientesAdmComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
  ],
  providers: [
    AdministradorService,
    provideNgxMask(),
  ]
})
export class AdministradorModule { }
