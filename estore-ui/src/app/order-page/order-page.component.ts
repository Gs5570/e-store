import { Component, OnInit } from '@angular/core';
import { OrderPageService } from '../order-page.service';
import { StoreProductServiceService } from '../store-product-service.service';
import { ShoppingCartProduct } from '../shopping-cart-product';
import { ShoppingCartItem } from '../cart-product';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { OrderProductItem } from '../order-product';

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.scss']
})
export class OrderPageComponent implements OnInit {
  constructor(private router: Router,private OrderPageService: OrderPageService, private userService: UserService, private productService: StoreProductServiceService) {}
  order: OrderProductItem[]=[];
  cartProduct: ShoppingCartProduct[] = [];
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
  updateCurrentOrderProduct(cart: ShoppingCartItem[]) {
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

      this.cartProduct.push(scp);
    }
    return this.cartProduct;
  }

  getorder(): void {
    this.OrderPageService
      .getorder(this.userService.userID)
      //.subscribe((curCart) => this.updateCurrentCartProduct());
  }



  ngOnInit(): void {
    this.getorder();
  }

}
