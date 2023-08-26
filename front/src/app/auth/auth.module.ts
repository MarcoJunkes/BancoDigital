import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';



@NgModule({
  declarations: [
    LoginComponent,
    CadastroComponent
  ],
  imports: [
    CommonModule,
    NgxMaskDirective,
    NgxMaskPipe,
  ],
  providers: [
    provideNgxMask(),
  ]
})
export class AuthModule { }
