import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from 'src/app/auth/services/login.service';
import { Endereco, Usuario, ViaCepService } from 'src/app/shared';
import { ContaService } from '../services/conta.service';
import { AutocadastroService } from 'src/app/auth/services/autocadastro.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-alterar-perfil',
  templateUrl: './alterar-perfil.component.html'
})
export class AlterarPerfilComponent implements OnInit {
  public endereco!: Endereco;
  public usuario!: Usuario;
  public saldo!: number;
  public gerente!: string;
  @ViewChild('formAlterarPerfil') formAlterarPerfil!: NgForm;

  constructor(
    private viacepService: ViaCepService,
    private loginService: LoginService,
    private contasService: ContaService,
    private autocadastroService: AutocadastroService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.usuario = this.loginService.usuarioLogado;
    this.contasService.buscarContaCliente().subscribe(
      ({conta, cliente}) => {
        this.saldo = conta.saldo || 0;
        this.usuario = cliente;
        this.gerente = conta.gerenteNome || '';
        this.endereco = new Endereco(
          this.usuario.cep,
          this.usuario.rua,
          this.usuario.numero,
          this.usuario.estado,
          this.usuario.bairro,
          this.usuario.cidade,
        );
      }
    );
  }

  buscaEndereco() {
    this.viacepService.getAddress(this.formAlterarPerfil.form.get('cep')?.value)
      .subscribe((address) => {
        this.formAlterarPerfil.form.patchValue({
          rua: address.logradouro,
          bairro: address.bairro,
          estado: address.uf,
          cidade: address.localidade
        });
      });
  }

  alterarPerfil() {
    this.usuario.cep = this.endereco.cep;
    this.usuario.numero = this.endereco.numero;
    this.usuario.rua = this.endereco.rua;
    this.usuario.estado = this.endereco.estado;
    this.usuario.bairro = this.endereco.bairro;
    this.usuario.cidade = this.endereco.cidade;
    
    this.autocadastroService.alterarPerfil(this.usuario).subscribe(
      () => this.router.navigate(["/cliente/home"])
    );
  }
}
