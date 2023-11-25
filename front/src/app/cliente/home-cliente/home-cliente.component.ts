import { Component, Input, OnInit } from '@angular/core';
import { ContaService } from '../services/conta.service';

@Component({
  selector: 'app-home-cliente',
  templateUrl: './home-cliente.component.html'
})
export class HomeClienteComponent implements OnInit {
  @Input() saldo: number = 0;

  constructor(private contasService: ContaService) { }
 
  ngOnInit(): void {
    this.contasService.buscarContaCliente().subscribe(
      conta => this.saldo = conta.conta.saldo || 0
    );
  }
}
