import { TestBed, inject } from '@angular/core/testing';

import { DrugService } from './drug.service';

describe('MedicamentoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DrugService]
    });
  });

  it('should be created', inject([DrugService], (service: DrugService) => {
    expect(service).toBeTruthy();
  }));
});
