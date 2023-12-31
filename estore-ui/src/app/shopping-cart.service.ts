import { Injectable } from '@angular/core';
import { ShoppingCartItem } from './cart-product';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ShoppingCartService {
  constructor(private http: HttpClient) {}

  private APIUrl = 'http://localhost:8080/shoppingcart';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  getcart(userID: Number): Observable<ShoppingCartItem[]> {
    const url = `${this.APIUrl}/${userID}`;
    return this.http.get<ShoppingCartItem[]>(url);
  }

  updateProduct(updatedProduct: ShoppingCartItem): Observable<any> {
    return this.http.put(
      this.APIUrl,
      {
        shoppingCartID: updatedProduct.shoppingCartID,
        shoppingCartQuantity: updatedProduct.shoppingCartQuantity,
      },
      this.httpOptions
    );
  }

  deleteProduct(id: Number): Observable<any> {
    const url = `${this.APIUrl}/${id}`;
    return this.http.delete<ShoppingCartItem>(url, this.httpOptions);
  }
  clearShoppingCart(userID: number): Observable<any> {
    const url = `${this.APIUrl}/?userID=${userID}`;
    return this.http.delete<ShoppingCartItem>(url, this.httpOptions);
  }

  addProduct(
    productID: Number,
    color: String,
    size: String,
    image: String,
    userID: Number
  ): Observable<any> {
    return this.http.post<ShoppingCartItem>(
      this.APIUrl,
      {
        productID: productID,
        color: color,
        size: size,
        image: image,
        userID: userID,
        shoppingCartQuantity: 1,
      },
      this.httpOptions
    );
  }
}
