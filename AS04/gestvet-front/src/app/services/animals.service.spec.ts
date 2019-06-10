import { TestBed, inject } from '@angular/core/testing';

import { AnimalsService } from './animals.service';

describe('AnimaisService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnimalsService]
    });
  });

  it('should be created', inject([AnimalsService], (service: AnimalsService) => {
    expect(service).toBeTruthy();
  }));
});
