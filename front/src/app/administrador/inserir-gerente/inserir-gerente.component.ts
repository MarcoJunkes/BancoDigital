import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { AdministradorService } from '../services/administrador.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-gerente',
  templateUrl: './inserir-gerente.component.html',
  styleUrls: ['./inserir-gerente.component.css']
})
export class InserirGerenteComponent implements OnInit {
  @ViewChild('formGerente') formGerente!: NgForm;
  gerente!: Gerente;

  constructor(
    private administradorService :AdministradorService,
    private router: Router) 
    { }

  ngOnInit(): void {
    this.gerente = new Gerente();
  }

  inserir(): void {
    if (this.formGerente.form.valid) {
    this.administradorService.inserir(this.gerente);
    this.router.navigate( ["/administrador/gerentes"] );
    }
    }
}
