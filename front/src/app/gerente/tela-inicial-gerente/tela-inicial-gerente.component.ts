import { Component, OnInit} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalTelaInicialComponent } from '../modal-tela-inicial/modal-tela-inicial.component';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-tela-inicial-gerente',
  templateUrl: './tela-inicial-gerente.component.html'
})
export class TelaInicialGerenteComponent implements OnInit{
  clientes: Cliente[] = [];

  constructor(private modalService: NgbModal, private gerenteService: GerenteService){}
  
  abrirModal(acao: string){
    const modalRef = this.modalService.open(ModalTelaInicialComponent);
    modalRef.componentInstance.acao = acao;
  }

  ngOnInit(): void {
      this.clientes = [];
      this.listarTodos();
  }

  listarTodos(): Cliente[] {
    this.gerenteService.listarTodos().subscribe({
      next: (data: Cliente[]) => {
        if (data == null) {
          this.clientes = [];
        }
        else {
          this.clientes = data;
        }
      }
    });
    return this.clientes;
  }
}
