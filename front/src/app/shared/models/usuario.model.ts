export class Usuario {
  constructor (
    public cpf?: string, 
    public nome?: string, 
    public email?: string, 
    public senha?: string,
    public telefone?: string, 
    public endereco?: string, // acho q pode apagar
    public perfil?: string,
    public salario?: number, 
    // Endereço.model
    public cep?: string,  
    public rua?: string, 
    public numero?: string, 
    public estado?: string, 
    public bairro?: string, 
    public cidade?: string, 
  ){}
}
