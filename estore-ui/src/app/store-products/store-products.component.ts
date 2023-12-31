import { Component, OnInit } from '@angular/core';
import {StoreProduct} from '../store-product';
// import{STOREPRODUCTS} from '../mock-storeProducts'
import { StoreProductServiceService } from '../store-product-service.service';
// import { MessageService } from '../message.service';

@Component({
  selector: 'app-store-products',
  templateUrl: './store-products.component.html',
  styleUrls: ['./store-products.component.scss']
})
export class StoreProductsComponent implements OnInit {
  //holds the mock store products list
  // storeProducts = STOREPRODUCTS;

  selectedStoreProduct?: StoreProduct;

  //holds the store product
  storeProducts: StoreProduct[] = [];

  // storeProduct: StoreProduct={
  //   id: 1,
  //   name:'mug',
  //   price: 10,
  //   quantity: 2,
  // }

  constructor(private storeProductService: StoreProductServiceService) { }

  
  ngOnInit(): void {
    this.getStoreProducts();
  }

  // onSelect(storeProduct: StoreProduct): void {
  //   this.selectedStoreProduct = storeProduct;
  //   this.messageService.add(`StoreProductsComponent: SelectedStoreProduct id=${storeProduct.id}, name=${storeProduct.name}, price=${storeProduct.price}, quantity=${storeProduct.quantity}`)
  // }

  //method to return the mock store product.
  getStoreProducts(): void {
    this.storeProductService.getStoreProducts()
        .subscribe(storeProducts => this.storeProducts = storeProducts);
  }

  add(id: any, name: string, price: any, quantity: any): void {  // the argument should have been number instead of any
    name = name.trim();
    if(!name) {return;}
    this.storeProductService.addStoreProduct({id: id, name: name, price: price, quantity: quantity} as StoreProduct)
      .subscribe(storeProduct => {
        this.storeProducts.push(storeProduct);
      })
  }
   delete (storeProduct: StoreProduct): void {
    this.storeProducts = this.storeProducts.filter(h => h !== storeProduct);
    this.storeProductService.deleteStoreProduct(storeProduct.id).subscribe();  // can delete by name by using storeProduct.name
   }


}
