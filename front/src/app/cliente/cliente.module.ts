import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutocadastroComponent } from './autocadastro/autocadastro.component';
import { LoginLogoutComponent } from './login-logout/login-logout.component';
import { HomeComponent } from './home/home.component';
import { AlterarPerfilComponent } from './alterar-perfil/alterar-perfil.component';
import { DepositarComponent } from './depositar/depositar.component';
import { DepositoComponent } from './deposito/deposito.component';
import { SaqueComponent } from './saque/saque.component';
import { TransferenciaComponent } from './transferencia/transferencia.component';
import { ConsultaSaldoComponent } from './consulta-saldo/consulta-saldo.component';
import { ConsultaExtratoComponent } from './consulta-extrato/consulta-extrato.component';



@NgModule({
  declarations: [
    AutocadastroComponent,
    LoginLogoutComponent,
    HomeComponent,
    AlterarPerfilComponent,
    DepositarComponent,
    DepositoComponent,
    SaqueComponent,
    TransferenciaComponent,
    ConsultaSaldoComponent,
    ConsultaExtratoComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ClienteModule { }
