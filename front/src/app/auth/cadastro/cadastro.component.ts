import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Endereco, Usuario, ViaCepService } from 'src/app/shared';
import { Router } from '@angular/router';
import { AutocadastroService } from '../services/autocadastro.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html'
})
export class CadastroComponent implements OnInit {
  public endereco!: Endereco;
  public usuario!: Usuario;
  public message!: string;
  @ViewChild('formCadastro') formCadastro!: NgForm;

  constructor(
    private viacepService: ViaCepService,
    private autocadastroService: AutocadastroService,
    private router: Router
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

  cadastro(): void {
    if (this.formCadastro.form.valid) {
      this.autocadastroService.autocadastro(this.usuario).subscribe(
        () => {
          this.router.navigate(["/login"]);
        }
      );
    }
  }
}
