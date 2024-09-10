import { TestBed } from '@angular/core/testing';

import { UnidadmedidaService } from './unidadmedida.service';

describe('UnidadmedidaService', () => {
  let service: UnidadmedidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnidadmedidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
