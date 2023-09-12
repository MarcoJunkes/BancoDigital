import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-cliente',
  templateUrl: './modal-cliente.component.html'
})
export class ModalClienteComponent {
  constructor(public activeModal: NgbActiveModal){}
  ngOnInit(): void{

  }
}