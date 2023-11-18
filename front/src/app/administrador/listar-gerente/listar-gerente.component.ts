import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../services/administrador.service';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-listar-gerente',
  templateUrl: './listar-gerente.component.html'
})
export class ListarGerenteComponent implements OnInit {

  gerentes: Gerente[] = [];

  constructor(private adminService: AdministradorService) { }

  ngOnInit(): void {
    /* Angtigo
    this.gerentes = this.listarTodos();*/
    this.listarTodosGerentes();
  }

  listarTodosGerentes(): void {
    this.adminService.listarTodosGerentes().subscribe((gerentes: Gerente[]) => {
      this.gerentes = gerentes;
    })
  }

  remover($event: any, gerente: Gerente): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o gerente ${gerente.nome}?`)) {
      this.adminService.removerGerente(gerente.id!).subscribe({
        next: () => {
          this.listarTodosGerentes();
        },
        error: (error: HttpErrorResponse) => {
          if (error.status === 403) {
            alert("Esse gerente não pode ser removido, pois existem contas vinculadas à ele.");
          }
        }
      });
    }
  }
/* Antigo
  listarTodos(): void[] {
    return this.adminService.listarTodos();
  }*/

/* Antigo
  remover($event: any, gerente: Gerente): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a pessoa ${gerente.nome}?`)) {
      this.adminService.remover(gerente.id!);
      this.gerentes = this.listarTodos();
    }
  } */
}
