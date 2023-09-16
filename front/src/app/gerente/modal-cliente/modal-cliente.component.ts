import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Cliente } from 'src/app/shared/models/cliente.model';
import { Conta } from 'src/app/shared/models/conta.model';

@Component({
  selector: 'app-modal-cliente',
  templateUrl: './modal-cliente.component.html'
})
export class ModalClienteComponent implements OnInit{
  @Input() cliente: Cliente = new Cliente();
  @Input() conta: Conta = new Conta();
  constructor(public activeModal: NgbActiveModal){}
  ngOnInit(): void{

  }
}