import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { StoreProductServiceService } from '../store-product-service.service';
import { ShoppingCartProduct } from '../shopping-cart-product';
import { ShoppingCartItem } from '../cart-product';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
})
export class ShoppingCartComponent implements OnInit {
  constructor(
    private router: Router,
    private ShoppingCartService: ShoppingCartService,
    private userService: UserService,
    private productService: StoreProductServiceService
  ) {}
  cart: ShoppingCartItem[] = [];
  cartProduct: ShoppingCartProduct[] = [];
  unsavedChanges: boolean[] = [];
  returnCartProduct(i: number): ShoppingCartProduct {
    return {
      shoppingCartID: this.cartProduct[i].shoppingCartID,
      productID: this.cartProduct[i].productID,
      color: this.cartProduct[i].color,
      size: this.cartProduct[i].size,
      image: this.cartProduct[i].image,
      userID: this.cartProduct[i].userID,
      shoppingCartQuantity: +this.cartProduct[i].shoppingCartQuantity,
      name: this.cartProduct[i].name,
      description: this.cartProduct[i].description,
      quantity: this.cartProduct[i].quantity,
      price: this.cartProduct[i].price,
      type: this.cartProduct[i].type,
      defaultImage: this.cartProduct[i].defaultImage,
    };
  }

  subtotal(i: number): number {
    return this.cartProduct[i].price * this.cartProduct[i].shoppingCartQuantity;
  }

	updateQuantity(i: number): void {
		  this.ShoppingCartService.updateProduct(
			this.returnCartProduct(i)
		  ).subscribe(() => {
			this.getcart();
		  });
	  }

  updateCurrentCartProduct(cart: ShoppingCartItem[]) {
    this.cartProduct = [];
    for (var cp of cart) {
      var _productID = cp.productID;
      const scp: ShoppingCartProduct = {
        shoppingCartID: cp.shoppingCartID,
        productID: _productID,
        color: cp.color,
        size: cp.size,
        image: cp.image,
        userID: cp.userID,
        shoppingCartQuantity: +cp.shoppingCartQuantity,
        name: '',
        description: '',
        quantity: 0,
        price: 0,
        type: '',
        defaultImage: '',
      };
      console.log('subscribing to get product details!');
      this.productService
        .getStoreProduct(_productID)
        .subscribe((storeProduct) => {
          scp.name = storeProduct.name;
          scp.description = storeProduct.description;
          scp.quantity = storeProduct.quantity;
          scp.price = storeProduct.price;
          scp.type = storeProduct.type;
          scp.defaultImage = storeProduct.image;
        });

      this.cartProduct.push(scp);
    }
    return this.cartProduct;
  }

  ProdJSON(i: number): string {
    return JSON.stringify(this.cartProduct[i]);
  }

  getcart(): void {
    this.ShoppingCartService.getcart(this.userService.userID).subscribe(
      (curCart) => this.updateCurrentCartProduct(curCart)
    );
  }

  delete(i: number): void {
    if (confirm('Are you sure?')) {
      this.ShoppingCartService.deleteProduct(
        this.returnCartProduct(i).shoppingCartID
      ).subscribe(() => {
        this.getcart();
      });
    }
  }
  save(i: number): void {
    console.log(this.returnCartProduct(i));
    // if (
    //   (<HTMLInputElement>document.getElementById(`product-name${i}`)).value ==
    //   '' ||
    //   (<HTMLInputElement>document.getElementById(`product-price${i}`)).value ==
    //   '' ||
    //   (<HTMLInputElement>document.getElementById(`product-quantity${i}`)).value ==
    //   ''
    // ) {
    //   alert('Before saving, please fix the invalid fields.');
    //   return;
    // }
    this.ShoppingCartService.updateProduct(this.returnCartProduct(i)).subscribe(
      () => {
        console.log('success!');
        this.unsavedChanges[i] = false;
      }
    );
    return;
  }
  clear() {
    if (confirm('Are you sure?')) {
      this.ShoppingCartService.clearShoppingCart(
        this.userService.userID
      ).subscribe(() => {
        this.getcart();
      });
    }
  }

  goToCheckoutPage() {
    this.router.navigate(['/check-out']);
  }

  ngOnInit(): void {
    this.getcart();
  }
}
