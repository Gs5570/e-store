import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomizedProductComponent } from './customized-product.component';

describe('CustomizedProductComponent', () => {
  let component: CustomizedProductComponent;
  let fixture: ComponentFixture<CustomizedProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomizedProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomizedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
