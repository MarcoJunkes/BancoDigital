import { Component, Input, OnInit } from '@angular/core';
import { GerenteService } from 'src/app/gerente/services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-home-cliente',
  templateUrl: './home-cliente.component.html'
})
export class HomeClienteComponent{
  @Input() saldo: number = -100;
  // clientes: Cliente[] = [];
  // constructor(private gerenteService : GerenteService){}
      
}
