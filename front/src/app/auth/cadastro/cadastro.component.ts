import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco, Usuario, ViaCepService } from 'src/app/shared';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html'
})
export class CadastroComponent implements OnInit {
  public endereco!: Endereco;
  public usuario!: Usuario;
  public message!: string;
  // public loading: boolean;
  @ViewChild('formCadastro') formCadastro!: NgForm;

  constructor(
    private viacepService: ViaCepService, 
    // private cadastroService: CadastroService,
    private loginService: LoginService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.usuario = new Usuario();
  }

  buscaEndereco() {
    this.viacepService.getAddress(this.formCadastro.form.get('cep')?.value)
      .subscribe((address) => {
        this.formCadastro.form.patchValue({
          rua: address.logradouro,
          bairro: address.bairro,
          estado: address.uf,
          cidade: address.localidade
        });
      });
  }

  limparForm() {
    this.formCadastro.reset({});
  }

  cadastro() {
    // if (this.formCadastro.invalid) return;
    this.formCadastro.reset({});
  }
}
