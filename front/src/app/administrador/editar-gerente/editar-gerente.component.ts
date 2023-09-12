import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdministradorService } from '../services/administrador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Gerente } from 'src/app/shared/models/gerente.model';

@Component({
  selector: 'app-editar-gerente',
  templateUrl: './editar-gerente.component.html',
  styleUrls: ['./editar-gerente.component.css']
})
export class EditarGerenteComponent implements OnInit {

  @ViewChild('formGerente') formGerente!: NgForm;

  gerente!: Gerente;

  constructor(
    private administradorService: AdministradorService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    // Com o id, obtém a pessoa
    const res = this.administradorService.buscarPorId(id);
    if (res !== undefined)
      this.gerente = res;
    else
      throw new Error("Pessoa não encontrada: id = " + id);
  }
  atualizar(): void {
    // Verifica se o formulário é válido
    if (this.formGerente.form.valid) {
      // Efetivamente atualiza a pessoa
      this.administradorService.atualizar(this.gerente);
      // Redireciona para /pessoas/listar
      this.router.navigate(['/administrador/gerentes']);
    }
  }

}
