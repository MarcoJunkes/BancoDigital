import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Saque } from 'src/app/shared';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html'
})
export class SaqueComponent implements OnInit {
  public saque!: Saque;
  @ViewChild('formSaque') formSaque!: NgForm;

  constructor() { }

  ngOnInit(): void {
    this.saque = new Saque();
  }
}
