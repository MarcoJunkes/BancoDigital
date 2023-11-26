export class Gerente {
    constructor(
        public id?: number,
        public nome?: string,
        public email?: string,
        public cpf?: string,
        public telefone?: string,
        public numeroClientes?: number,
        public saldoPositivo?: number,
        public saldoNegativo?: number,
        public gerenteNome?: string
    ) {}
}
