import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from 'src/app/auth/services/login.service';
import { Endereco, Usuario, ViaCepService } from 'src/app/shared';

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
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.usuario = this.loginService.usuarioLogado;
    this.saldo = 123;
    this.gerente = 'Fulano';
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
    if (this.formAlterarPerfil.invalid) return;
    this.formAlterarPerfil.reset({});
  }
}
