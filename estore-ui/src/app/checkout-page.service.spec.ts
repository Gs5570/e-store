import { TestBed } from '@angular/core/testing';

import { CheckoutPageService } from './checkout-page.service';

describe('CheckoutPageService', () => {
  let service: CheckoutPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckoutPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
