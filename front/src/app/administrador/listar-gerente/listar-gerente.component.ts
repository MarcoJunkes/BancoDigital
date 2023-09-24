import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../services/administrador.service';
import { Gerente } from 'src/app/shared/models/gerente.model';

@Component({
  selector: 'app-listar-gerente',
  templateUrl: './listar-gerente.component.html'
})
export class ListarGerenteComponent implements OnInit {

  gerentes: Gerente[] = [];

  constructor(private administradorService: AdministradorService) { }

  ngOnInit(): void {
    this.gerentes = this.listarTodos();
  }

  listarTodos(): Gerente[] {
    return this.administradorService.listarTodos();
  }
  remover($event: any, gerente: Gerente): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a pessoa ${gerente.nome}?`)) {
      this.administradorService.remover(gerente.id!);
      this.gerentes = this.listarTodos();
    }
  }
}
