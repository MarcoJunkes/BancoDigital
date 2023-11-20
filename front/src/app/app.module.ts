import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './layout';
import { AppRoutingModule } from './app-routing.module';
import { ClienteModule } from './cliente';
import { AdministradorModule } from './administrador/administrador.module';
import { GerenteModule } from './gerente';
import { ContaModule } from './conta';
import { NgbModal, NgbModalConfig, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { AuthModule } from './auth/auth.module';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { httpInterceptorProviders } from './http-interceptors';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    AuthModule,
    ClienteModule,
    AdministradorModule,
    GerenteModule,
    ContaModule,
    NgbModule,
    HttpClientModule,
  ],
  providers: [
    NgbModalConfig, 
    NgbModal,
    AuthInterceptor,
    ...httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
