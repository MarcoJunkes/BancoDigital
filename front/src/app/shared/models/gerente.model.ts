export class Gerente {
    constructor(
        public id?: number,
        public nome?: string,
        public email?: string,
        public cpf?: string,
        public telefone?: string,
        public quantidadeContas?: number,
        public somaSaldosPositivos?: number,
        public somaSaldosNegativos?: number,
    ) {}
}
