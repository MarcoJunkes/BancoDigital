import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Cliente } from 'src/app/shared/models/cliente.model';

@Component({
  selector: 'app-modal-cliente',
  templateUrl: './modal-cliente.component.html'
})
export class ModalClienteComponent implements OnInit{
  @Input() cliente: Cliente = new Cliente();
  constructor(public activeModal: NgbActiveModal){}
  ngOnInit(): void{

  }
}