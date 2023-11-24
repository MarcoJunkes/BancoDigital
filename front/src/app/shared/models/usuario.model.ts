export class Usuario {
  constructor (
    public cpf?: string, //ok
    public nome?: string, //ok
    public email?: string, //ok
    public senha?: string,
    public telefone?: string, //ok
    public endereco?: string, // acho q pode apagar
    public perfil?: string,
    public salario?: number, //ok
    // Endereço.model
    public cep?: string,  //ok
    public rua?: string, //ok
    public numero?: string, //ok
    public estado?: string, //ok
    public bairro?: string, //ok
    public cidade?: string, //ok
  ){}
}
