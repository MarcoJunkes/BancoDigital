import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OperacaoDirecao, OperacaoTipo, Transferencia } from 'src/app/shared';
import { OperacaoService } from '../services/operacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html'
})
export class TransferenciaComponent implements OnInit {
  public transferencia!: Transferencia;
  @ViewChild('transferenciaForm') transferenciaForm!: NgForm;

  constructor(private readonly operacaoService: OperacaoService, private router: Router) {}

  ngOnInit(): void {
    this.transferencia = new Transferencia(OperacaoTipo.TRANSFERENCIA, new Date().toISOString().split('T')[0], OperacaoDirecao.SAIDA);
  }

  transferir(): void {
    this.operacaoService.salvar(this.transferencia).subscribe(
      () => this.router.navigate(['/cliente/home'])
    );
  }
}
