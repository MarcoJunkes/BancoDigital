import { NgModule, DEFAULT_CURRENCY_CODE, LOCALE_ID } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { ContultarTodosClientesComponent } from './contultar-todos-clientes/contultar-todos-clientes.component';
import { ModalClienteComponent } from './modal-cliente/modal-cliente.component';
import { GerenteService } from './services/gerente.service';
import { FormsModule } from '@angular/forms';
import { TelaInicialGerenteComponent } from './tela-inicial-gerente/tela-inicial-gerente.component';
import { RouterModule } from '@angular/router';
import { ConsultarClienteComponent } from './consultar-cliente/consultar-cliente.component';
import { ModalTelaInicialComponent } from './modal-tela-inicial/modal-tela-inicial.component';
import { ConsultarTresMelhoresClientesComponent } from './consultar-tres-melhores-clientes/consultar-tres-melhores-clientes.component';
// Máscara dinheiro
import ptBr from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
// Máscara data
import { VERSION } from '@angular/core';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
registerLocaleData(ptBr);

@NgModule({
  declarations: [
    ContultarTodosClientesComponent,
    ModalClienteComponent,
    TelaInicialGerenteComponent,
    ConsultarClienteComponent,
    ModalTelaInicialComponent,
    ConsultarTresMelhoresClientesComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NgxMaskDirective,
    NgxMaskPipe
  ],
  providers: [
    provideNgxMask(),
    GerenteService,
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR'
    },

    {
        provide:  DEFAULT_CURRENCY_CODE,
        useValue: 'BRL'
    },
  ]
})
export class GerenteModule {
}
