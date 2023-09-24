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
    public id?: number,
    public tipo?: OperacaoTipo,
    public valor?: number,
    public data?: string,
    public origem?: string,
    public destino?: string,
    public direcao?: OperacaoDirecao
  ) {}
}