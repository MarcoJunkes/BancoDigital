import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGerentesComponent } from './admin-gerentes.component';

describe('AdminGerentesComponent', () => {
  let component: AdminGerentesComponent;
  let fixture: ComponentFixture<AdminGerentesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGerentesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGerentesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
