import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './layout';
import { AppRoutingModule } from './app-routing.module';
import { ClienteModule } from './cliente';
import { FormsModule } from '@angular/forms';
import { GerenteModule } from './gerente';
import { ContaModule } from './conta';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClienteModule,
    GerenteModule,
    ContaModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
