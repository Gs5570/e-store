import { Component, OnInit } from '@angular/core';
import { StoreProduct } from '../store-product';
import { StoreProductServiceService } from '../store-product-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  storeProducts: StoreProduct[] = [];

  constructor(private storeProductService: StoreProductServiceService) {}

  ngOnInit(): void {
    this.getStoreProducts();
  }

  getStoreProducts(): void {
    this.storeProductService
      .getStoreProducts()
      .subscribe((storeProducts) => (this.storeProducts = storeProducts));
  }
}
