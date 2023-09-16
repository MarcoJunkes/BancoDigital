export class Usuario {
  constructor (
    public cpf?: string,
    public nome?: string,
    public email?: string,
    public senha?: string,
    public telefone?: string,
    public endereco?: string,
    public perfil?: string,
    public salario?: number,
  ){}
}
