import { Injectable } from '@angular/core';
import { ShoppingCartItem } from './cart-product';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
 

@Injectable({
  providedIn: 'root'
})
export class CheckoutPageService {

  constructor(private http: HttpClient) {}
  private APIUrl = 'http://localhost:8080/shoppingcart';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };
  getcart(userID: Number): Observable<ShoppingCartItem[]> {
    const url = `${this.APIUrl}/${userID}`;
    return this.http.get<ShoppingCartItem[]>(url);
  }
}