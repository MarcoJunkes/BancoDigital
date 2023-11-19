import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdministradorService } from '../services/administrador.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Gerente } from 'src/app/shared/models/gerente.model';

@Component({
  selector: 'app-editar-gerente',
  templateUrl: './editar-gerente.component.html'
})
export class EditarGerenteComponent implements OnInit {

  @ViewChild('formGerente') formGerente!: NgForm;
  loading!: boolean;

  gerente!: Gerente;

  constructor(
    private adminService: AdministradorService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    // snapshot.params de ActivatedRoute dá acesso aos parâmetros passados
    // Operador + (antes do this) converte para número
    let id = +this.route.snapshot.params['id'];
    this.loading = false;
    this.adminService.buscarGerentePorId(id).subscribe((gerente: Gerente) => {
      this.gerente = gerente;
    });
  }
  atualizar(): void {
    this.loading = true;
    if(this.formGerente.form.valid) {
      this.adminService.atualizarGerente(this.gerente).subscribe((gerente) => {
        this.loading = false;
        this.router.navigate( ["/administrador/gerentes"]);
      });
    }
    this.loading = false;
  }
}
