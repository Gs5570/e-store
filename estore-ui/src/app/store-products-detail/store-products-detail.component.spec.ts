import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreProductsDetailComponent } from './store-products-detail.component';

describe('StoreProductsDetailComponent', () => {
  let component: StoreProductsDetailComponent;
  let fixture: ComponentFixture<StoreProductsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StoreProductsDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoreProductsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
