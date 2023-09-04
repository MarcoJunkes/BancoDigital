import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTelaInicialComponent } from './modal-tela-inicial.component';

describe('ModalTelaInicialComponent', () => {
  let component: ModalTelaInicialComponent;
  let fixture: ComponentFixture<ModalTelaInicialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalTelaInicialComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalTelaInicialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
