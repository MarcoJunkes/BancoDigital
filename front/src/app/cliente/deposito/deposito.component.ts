import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Deposito, OperacaoDirecao, OperacaoTipo } from 'src/app/shared';
import { OperacaoService } from '../services/operacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html'
})
export class DepositoComponent {
  public deposito!: Deposito;
  @ViewChild('formDeposito') formDeposito!: NgForm;

  constructor(private readonly operacaoService: OperacaoService, private router: Router) { }

  ngOnInit(): void {
    this.deposito = new Deposito(OperacaoTipo.DEPOSITO, new Date().toISOString().split('T')[0], OperacaoDirecao.ENTRADA);
  }

  depositar(): void {
    this.operacaoService.salvar(this.deposito).subscribe(
      () => this.router.navigate(['/cliente/home'])
    );
  }
}
