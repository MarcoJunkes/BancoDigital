export enum OperacaoDirecao {
  ENTRADA = 'ENTRADA',
  SAIDA = 'SAIDA',
}

export enum OperacaoTipo {
  TRANSFERENCIA = 'TRANSFERENCIA',
  SAQUE = 'SAQUE',
  DEPOSITO = 'DEPOSITO',
}

export class Operacao {
  constructor(
    public tipo?: OperacaoTipo,
    public data?: string,
    public direcao?: OperacaoDirecao,
    public id?: number,
    public valor?: number,
    public origem?: string,
    public destino?: string,
  ) {}
}