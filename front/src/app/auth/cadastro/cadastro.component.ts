import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco, Usuario, ViaCepService } from 'src/app/shared';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { autocadastroService } from '../services/autocadastro.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html'
})
export class CadastroComponent implements OnInit {
  public endereco!: Endereco;
  public usuario!: Usuario;
  public message!: string;
  // private cliente!: Cliente;
  @ViewChild('formCadastro') formCadastro!: NgForm;

  constructor(
    private viacepService: ViaCepService,
    private autocadastroService: autocadastroService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.endereco = new Endereco();
    this.usuario = new Usuario();
    // this.cliente = new Cliente();
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

  cadastro(): void {
    if (this.formCadastro.form.valid) {
      this.autocadastroService.autocadastro(this.usuario).subscribe(
        usuario => {
          this.router.navigate( ["/login"]);
        }
      );
      // Está enviando os dados certinho
    }
  }
}
