import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarClientesAdmComponent } from './consultar-clientes-adm.component';

describe('ConsultarClientesAdmComponent', () => {
  let component: ConsultarClientesAdmComponent;
  let fixture: ComponentFixture<ConsultarClientesAdmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarClientesAdmComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarClientesAdmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
