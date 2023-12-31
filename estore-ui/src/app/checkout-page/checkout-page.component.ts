import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../shopping-cart.service';
import { StoreProductServiceService } from '../store-product-service.service';
import { ShoppingCartProduct } from '../shopping-cart-product';
import { ShoppingCartItem } from '../cart-product';
import { UserService } from '../user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.scss']
})
export class CheckoutPageComponent implements OnInit {
  public showMyMessage = false

  constructor( private router: Router,private ShoppingCartService: ShoppingCartService, private userService: UserService, private productService: StoreProductServiceService) {}
  cart: ShoppingCartItem[] = [];
  cartProduct: ShoppingCartProduct[] = [];
  total=0;
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

  async updateCurrentCartProduct(cart: ShoppingCartItem[]) {
    this.cartProduct = [];

    for(var cp of cart) {
      var _productID = cp.productID;
      const scp: ShoppingCartProduct = {
        shoppingCartID: cp.shoppingCartID,
        productID: _productID,
        color: cp.color,
        size: cp.size,
        image: cp.image,
        userID: cp.userID,
        shoppingCartQuantity: +cp.shoppingCartQuantity,
        name: "",
        description: "",
        quantity: 0,
        price: 0,
        type: "",
        defaultImage: "",
      };
      console.log("subscribing to get product details!")
      this.productService.getStoreProduct(_productID).subscribe((storeProduct) => {
        scp.name = storeProduct.name;
        scp.description = storeProduct.description;
        scp.quantity = storeProduct.quantity;
        scp.price = storeProduct.price;
        scp.type = storeProduct.type;
        scp.defaultImage = storeProduct.image;
      });

      await new Promise(f => setTimeout(f, 100));
      this.total+= scp.price*scp.shoppingCartQuantity;
      //this.total+=20;
      this.cartProduct.push(scp);
    }
  
    return this.cartProduct;
  }

  getcart(): void {
    this.ShoppingCartService
      .getcart(this.userService.userID)
      .subscribe((curCart) => this.updateCurrentCartProduct(curCart));
  }

  ngOnInit(): void {
    this.getcart();
  }

  showMessageSoon(){
    setTimeout(() => {
      this.showMyMessage = true
    }, 300)

    this.clear()
    this.router.navigate(['/thank-you']);

  }

  clear() {
    this.ShoppingCartService.clearShoppingCart(
      this.userService.userID
    ).subscribe(() => {
      this.getcart();
    });
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
}

}
