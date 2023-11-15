export class Gerente {
    constructor(
        public id?: string, /*Estava number, mas estava dando erro */
        public nome?: string,
        public email?: string,
        public cpf?: string,
        public telefone?: string
    ) {}
}
