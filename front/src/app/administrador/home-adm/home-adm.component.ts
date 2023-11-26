import { Component, OnInit } from '@angular/core';
import { Gerente } from 'src/app/shared/models/gerente.model';
import { AdministradorService } from '../services/administrador.service';

@Component({
  selector: 'app-home-adm',
  templateUrl: './home-adm.component.html'
})
export class HomeAdmComponent implements OnInit {

  gerentes: Gerente[] = [];

  constructor(private adminService: AdministradorService) {}

  ngOnInit(): void {
    this.listarTodosGerentes();
  }

  listarTodosGerentes() {
    this.adminService.listarTodosGerentes2().subscribe(
      (response: any) => {
        this.gerentes = this.gerentes.concat(response.conta);
      }
    );
  }
}
