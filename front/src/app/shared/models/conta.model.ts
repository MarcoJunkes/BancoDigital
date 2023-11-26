export class Conta {
    constructor(
        public numero?: number,
        public saldo?: number,
        public limite?: number,
        public gerenteCpf?: string,
        public gerenteNome?: string,
    ){}
}
