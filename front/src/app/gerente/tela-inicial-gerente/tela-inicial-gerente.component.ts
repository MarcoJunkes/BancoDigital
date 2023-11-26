import { Component, OnInit} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalTelaInicialComponent } from '../modal-tela-inicial/modal-tela-inicial.component';
import { GerenteService } from '../services/gerente.service';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-tela-inicial-gerente',
  templateUrl: './tela-inicial-gerente.component.html'
})
export class TelaInicialGerenteComponent implements OnInit {
  clientes: Cliente[] = [];

  constructor(
    private modalService: NgbModal,
    private gerenteService: GerenteService
  ) {}
  
  ngOnInit(): void {
      this.listarTodos();
  }

  listarTodos(): void {
    const filtro = {
      status: 'PENDENTE_APROVACAO'
    }
    this.gerenteService.listarTodos(filtro).subscribe(({clientes}) => this.clientes = clientes);
  }

  abrirModal(acao: string){
    const modalRef = this.modalService.open(ModalTelaInicialComponent);
    modalRef.componentInstance.acao = acao;
  }

  aprovarCliente(cliente: Cliente){
    this.gerenteService.aprovarCliente(cliente).subscribe(
      cliente => {
        this.abrirModal('Aprovar');
      }
    );
  }

  rejeitarCliente(cliente: Cliente) {
    this.gerenteService.rejeitarCliente(cliente).subscribe(
      cliente => {
        this.abrirModal('Recusar')
      }
    );
  }
}
