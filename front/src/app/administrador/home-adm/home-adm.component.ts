import { Component, OnInit } from '@angular/core';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { AdministradorService } from '../services/administrador.service';

@Component({
  selector: 'app-home-adm',
  templateUrl: './home-adm.component.html'
})
export class HomeAdmComponent implements OnInit{

  gerentes: Gerente[] =[];

  constructor(private adminService: AdministradorService) {}

  ngOnInit(): void {
    this.listarTodosGerentes();
  }

  /*Dinâmico
  listarTodosGerentes() {
    this.adminService.listarTodosGerentes()
      .subscribe((gerentes: Gerente[]) => {
        this.gerentes = gerentes;
      });
  } */
  /*Estático */
  listarTodosGerentes() {
    this.gerentes = [
      {
        "id": "1",
        "nome": "Gerente 1",
        "email": "gerente1@email.com",
        "cpf": "12345678912",
        "quantidadeContas": 120,
        "somaSaldosPositivos": 14000,
        "somaSaldosNegativos": 1000
      },
      {
        "id": "2",
        "nome": "Gerente 2",
        "email": "gerente2@email.com",
        "cpf": "12345678913",
        "quantidadeContas": 200,
        "somaSaldosPositivos": 168423,
        "somaSaldosNegativos": 1500
      }
    ]
  }
}
