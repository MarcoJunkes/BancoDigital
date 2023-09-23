import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Transferencia } from 'src/app/shared';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html'
})
export class TransferenciaComponent implements OnInit {
  public transferencia!: Transferencia;
  @ViewChild('transferenciaForm') transferenciaForm!: NgForm;

  constructor() {

  }

  ngOnInit(): void {
    this.transferencia = new Transferencia();
  }

  transferir(): void {
    console.log(this.transferencia);
  }

  voltar(): void {
    console.log('voltar');
  }
}
