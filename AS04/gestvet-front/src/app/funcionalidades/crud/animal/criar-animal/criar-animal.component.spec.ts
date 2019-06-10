import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriarAnimalComponent } from './criar-animal.component';

describe('CriarAnimalComponent', () => {
  let component: CriarAnimalComponent;
  let fixture: ComponentFixture<CriarAnimalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriarAnimalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriarAnimalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
