import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { AdministradorService } from '../services/administrador.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-gerente',
  templateUrl: './inserir-gerente.component.html'
})
export class InserirGerenteComponent implements OnInit {
  @ViewChild('formGerente') formGerente!: NgForm;
  gerente!: Gerente;
  loading!: boolean;

  constructor(
    private administradorService :AdministradorService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.gerente = new Gerente();
  }

  inserir(): void {
    this.loading = true;
    if (this.formGerente.form.valid) {
      this.administradorService.criarGerente(this.gerente).subscribe (
        gerente => {
          this.loading = false;
          this.router.navigate( ["/administrador/gerentes"]);
        }
      );
    }
    this.loading = false;
  }
}
