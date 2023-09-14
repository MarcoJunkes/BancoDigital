import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-tela-inicial',
  templateUrl: './modal-tela-inicial.component.html'
})
export class ModalTelaInicialComponent implements OnInit {
  @Input() acao: string | undefined;

  constructor(public activeModal: NgbActiveModal){}

  ngOnInit(): void {
     
  }

}
