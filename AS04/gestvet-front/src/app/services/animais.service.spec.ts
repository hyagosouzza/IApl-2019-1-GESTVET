import { TestBed, inject } from '@angular/core/testing';

import { AnimaisService } from './animais.service';

describe('AnimaisService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AnimaisService]
    });
  });

  it('should be created', inject([AnimaisService], (service: AnimaisService) => {
    expect(service).toBeTruthy();
  }));
});
