import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent, NavbarComponent } from './layout';
import { AppRoutingModule } from './app-routing.module';
import { ClienteModule } from './cliente';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClienteModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
