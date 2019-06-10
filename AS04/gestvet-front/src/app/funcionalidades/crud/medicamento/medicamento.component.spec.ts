import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentoComponent } from './medicamento.component';

describe('MedicamentoComponent', () => {
  let component: MedicamentoComponent;
  let fixture: ComponentFixture<MedicamentoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicamentoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
