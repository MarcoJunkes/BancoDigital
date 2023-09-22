import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlterarPerfilComponent } from './alterar-perfil/alterar-perfil.component';
import { DepositoComponent } from './deposito/deposito.component';
import { SaqueComponent } from './saque/saque.component';
import { TransferenciaComponent } from './transferencia/transferencia.component';
import { ConsultaExtratoComponent } from './consulta-extrato/consulta-extrato.component';
import { HomeClienteComponent } from './home-cliente/home-cliente.component';
import { RouterModule } from '@angular/router';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AlterarPerfilComponent,
    DepositoComponent,
    SaqueComponent,
    TransferenciaComponent,
    ConsultaExtratoComponent,
    HomeClienteComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe,
  ],
  providers: [
    provideNgxMask(),
  ]
})
export class ClienteModule { }
