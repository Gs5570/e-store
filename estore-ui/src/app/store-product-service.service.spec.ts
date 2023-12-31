import { TestBed } from '@angular/core/testing';

import { StoreProductServiceService } from './store-product-service.service';

describe('StoreProductServiceService', () => {
  let service: StoreProductServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StoreProductServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
