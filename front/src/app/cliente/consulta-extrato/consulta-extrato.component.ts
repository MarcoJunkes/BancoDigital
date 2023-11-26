import { Component, OnInit } from '@angular/core';
import { OperacaoService } from '../services/operacao.service';
import { Operacao, OperacaoDirecao } from 'src/app/shared';

@Component({
  selector: 'app-consulta-extrato',
  templateUrl: './consulta-extrato.component.html',
  styleUrls: ['./consulta-extrato.component.css']
})
export class ConsultaExtratoComponent implements OnInit {
  public filtro = {
    dataInicial: '',
    dataFinal: '',
  }
  public operacoes: { data: string, operacoes: Operacao[] }[] = [];

  constructor(private readonly operacaoService: OperacaoService) { }

  ngOnInit(): void {
    this.getOperacoes();
  }

  getOperacoes(): void {
    const dateComplementInicial = "T00:00:00.000Z";
    const dateComplementFinal = "T23:59:59.999Z";
    const dataInicial = this.filtro.dataInicial + dateComplementInicial;
    const dataFinal = this.filtro.dataFinal + dateComplementFinal;
    const filtro = {
      dataInicial: dataInicial === dateComplementInicial ? '' : dataInicial,
      dataFinal: dataFinal === dateComplementFinal ? '' : dataFinal,
    }

    this.operacaoService.listar(filtro).subscribe((res) => {
      this.operacoes = this.groupOperacoesByData(res);
    });
  }

  limparFiltro(): void {
    this.filtro = {
      dataInicial: '',
      dataFinal: '',
    }
    this.getOperacoes();
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
