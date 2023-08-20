import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlterarPerfilComponent } from './alterar-perfil/alterar-perfil.component';
import { DepositoComponent } from './deposito/deposito.component';
import { SaqueComponent } from './saque/saque.component';
import { TransferenciaComponent } from './transferencia/transferencia.component';
import { ConsultaSaldoComponent } from './consulta-saldo/consulta-saldo.component';
import { ConsultaExtratoComponent } from './consulta-extrato/consulta-extrato.component';
import { HomeClienteComponent } from './home-cliente';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    AlterarPerfilComponent,
    DepositoComponent,
    SaqueComponent,
    TransferenciaComponent,
    ConsultaSaldoComponent,
    ConsultaExtratoComponent,
    HomeClienteComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    HomeClienteComponent
  ]
})
export class ClienteModule { }
