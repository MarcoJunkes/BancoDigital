export class Endereco {
  constructor (
    public cep?: string,
    public rua?: string,
    public numero?: string,
    public estado?: string,
    public bairro?: string,
    public cidade?: string,
  ){}

  toString(): string {
    return `${this.rua}, ${this.numero} - ${this.bairro}, ${this.cidade} - ${this.estado}, ${this.cep}`
  }
}