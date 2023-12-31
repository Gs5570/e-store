import { TestBed } from '@angular/core/testing';

import { OrderPageService } from './order-page.service';

describe('OrderPageService', () => {
  let service: OrderPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
