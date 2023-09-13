import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './layout';
import { AppRoutingModule } from './app-routing.module';
import { ClienteModule } from './cliente';
import { GerenteModule } from './gerente';
import { ContaModule } from './conta';
import { NgbModal, NgbModalConfig, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { AuthModule } from './auth/auth.module';

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
    GerenteModule,
    ContaModule,
    NgbModule,
    HttpClientModule,
  ],
  providers: [
    NgbModalConfig, 
    NgbModal
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
