import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { InventoryComponent } from './inventory/inventory.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { StoreProductsComponent } from './store-products/store-products.component';
import { FormsModule } from '@angular/forms';
import { StoreProductsDetailComponent } from './store-products-detail/store-products-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { CustomizedProductComponent } from './customized-product/customized-product.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';
import { OrderPageComponent } from './order-page/order-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProductsComponent,
    ShoppingCartComponent,
    InventoryComponent,
    ProductSearchComponent,
    StoreProductsComponent,
    StoreProductsDetailComponent,
    MessagesComponent,
    DashboardComponent,
    ProductFormComponent,
    CustomizedProductComponent,
    CheckoutPageComponent,
    OrderPageComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
