import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './layout';
import { AppRoutingModule } from './app-routing.module';
import { ClienteModule } from './cliente';
import { GerenteModule } from './gerente';
import { ContaModule } from './conta';
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
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
