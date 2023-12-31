import { Injectable } from '@angular/core';
import { OrderProductItem } from './order-product';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ShoppingCartProduct } from './shopping-cart-product';


@Injectable({
  providedIn: 'root'
})
export class OrderPageService {
  constructor(private http: HttpClient) {}
  private APIUrl = 'http://localhost:8080/order';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };
  
  getorder(userID: Number): Observable<OrderProductItem[]> {
    const url = `${this.APIUrl}/${userID}`;
    return this.http.get<OrderProductItem[]>(url);
  }
  addProduct(userID:Number, items: ShoppingCartProduct[], orderDate:String, address: String):
  Observable<any>{
   console.log(userID);
   return this.http.put<OrderProductItem>(
     this.APIUrl,
     { 'userID': userID, 'items': items, 'orderDate': orderDate, 'address':address },
     this.httpOptions
   );
 }
}