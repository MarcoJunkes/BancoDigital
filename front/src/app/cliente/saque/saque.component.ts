import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OperacaoDirecao, OperacaoTipo, Saque } from 'src/app/shared';
import { OperacaoService } from '../services/operacao.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html'
})
export class SaqueComponent implements OnInit {
  public saque!: Saque;
  @ViewChild('formSaque') formSaque!: NgForm;

  constructor(private readonly operacaoService: OperacaoService, private router: Router) { }

  ngOnInit(): void {
    this.saque = new Saque(OperacaoTipo.SAQUE, new Date().toISOString().split('T')[0], OperacaoDirecao.SAIDA);
  }

  sacar(): void {
    this.operacaoService.salvar(this.saque).subscribe(
      () => this.router.navigate(['/cliente/home'])
    );
  }
}
