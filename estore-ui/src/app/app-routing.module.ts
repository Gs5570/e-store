import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { InventoryComponent } from './inventory/inventory.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
// import { ProductsComponent } from './products/products.component';

// import { StoreProductsComponent } from './store-products/store-products.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StoreProductsDetailComponent } from './store-products-detail/store-products-detail.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';
import { OrderPageComponent } from './order-page/order-page.component';

const routes: Routes = [
  //default route
  { path: 'sign-in', component: LoginComponent },
  { path: 'inventory', component: InventoryComponent },
  // { path: 'products', component: ProductsComponent },
  { path: 'products', component: DashboardComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent },
  { path: 'check-out', component: CheckoutPageComponent },
  { path: 'thank-you', component: OrderPageComponent },
  // { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  // { path: 'storeProducts', component: StoreProductsComponent },
  // { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: StoreProductsDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
