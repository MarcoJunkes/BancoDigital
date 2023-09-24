import { Component, OnInit } from '@angular/core';
import { OperacaoService } from '../services/operacao.service';
import { Operacao, OperacaoDirecao } from 'src/app/shared';

@Component({
  selector: 'app-consulta-extrato',
  templateUrl: './consulta-extrato.component.html',
  styleUrls: ['./consulta-extrato.component.css']
})
export class ConsultaExtratoComponent implements OnInit {
  public operacoes: { data: string, operacoes: Operacao[] }[] = [];

  constructor(private readonly operacaoService: OperacaoService) { }

  ngOnInit(): void {
    this.operacaoService.listar().subscribe((res) => {
      this.operacoes = this.groupOperacoesByData(res);
    });
  }

  groupOperacoesByData(operacoes: Operacao[]): { data: string, operacoes: Operacao[] }[] {
    const operacoesByData: { [key: string]: Operacao[] } = {};

    operacoes.forEach((operacao) => {
      const operacaoDatetime = new Date(operacao.data!);
      const data = operacaoDatetime.toISOString().split('T')[0];

      if (!operacoesByData[data]) {
        operacoesByData[data] = [];
      }

      operacoesByData[data].push(operacao);
    });

    const operacoesList = Object.keys(operacoesByData).map((data) => {
      return { data, operacoes: operacoesByData[data] };
    });

    return operacoesList;
  }

  getIconClass({ direcao }: Operacao) {
    return direcao === OperacaoDirecao.SAIDA ? 'minus' : 'plus'
  }

  getCardType(operacao: Operacao) {
    return operacao.direcao === OperacaoDirecao.ENTRADA ? 'income' : 'expense'
  }
}
