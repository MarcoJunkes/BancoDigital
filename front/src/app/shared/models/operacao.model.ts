export enum OperacaoDirecao {
  ENTRADA = 'entrada',
  SAIDA = 'saida',
}

export enum OperacaoTipo {
  TRANSFERENCIA = 'transferencia',
  SAQUE = 'saque',
  DEPOSITO = 'deposito',
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