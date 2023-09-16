export class Cliente {
    constructor(
        public id?: number,
        public nome?: string,
        public cpf?: string,
        public cidade?: string,
        public estado?: string,
        public saldo?: number,
        // Dados daqui para baixo são da conta. Apagar para implementar os microsserviços
        public dataCriacao?: Date,
        public limite?: number,
        public gerente?: string,
        public salario?: number
    ){}
}
