import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminGerentesComponent } from './admin-gerentes/admin-gerentes.component';

import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AdminGerentesComponent,
  ],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class AdministradorModule { }
