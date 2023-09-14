import { Component} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalTelaInicialComponent } from '../modal-tela-inicial/modal-tela-inicial.component';

@Component({
  selector: 'app-tela-inicial-gerente',
  templateUrl: './tela-inicial-gerente.component.html'
})
export class TelaInicialGerenteComponent {
  constructor(private modalService: NgbModal){}

  abrirModal(acao: string){
    const modalRef = this.modalService.open(ModalTelaInicialComponent);
    modalRef.componentInstance.acao = acao;
  }
}
