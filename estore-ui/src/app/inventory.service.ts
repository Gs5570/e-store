import { Injectable } from '@angular/core';
import { StoreProduct } from './store-product';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  constructor(private http: HttpClient) {}

  private APIUrl = 'http://localhost:8080/products';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  getInventory(): Observable<StoreProduct[]> {
    return this.http.get<StoreProduct[]>(this.APIUrl);
  }

  updateProduct(updatedProduct: StoreProduct): Observable<any> {
    return this.http.put(this.APIUrl, updatedProduct, this.httpOptions);
  }

  deleteProduct(product: StoreProduct): Observable<any> {
    const url = `${this.APIUrl}/${product.id}`;
    return this.http.delete<StoreProduct>(url, this.httpOptions);
  }

  newProduct(): Observable<any> {
    return this.http.post<StoreProduct>(
      this.APIUrl,
      { name: '', price: 0, quantity: 0 },
      this.httpOptions
    );
  }
}
