import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Deposito } from 'src/app/shared';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html'
})
export class DepositoComponent {
  public deposito!: Deposito;
  @ViewChild('formDeposito') formDeposito!: NgForm;

  constructor() { }

  ngOnInit(): void {
    this.deposito = new Deposito();
  }

}
