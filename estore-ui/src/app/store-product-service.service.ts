/**
 * service to get product data
 */

import { Injectable } from '@angular/core';
//import store produt array from mock store product
//store products interface
import { StoreProduct } from './store-product';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class StoreProductServiceService {
  private storeProductsUrl = 'http://localhost:8080/products'; // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  /** GET storeProducts from the server */
  getStoreProducts(): Observable<StoreProduct[]> {
    return this.http.get<StoreProduct[]>(this.storeProductsUrl).pipe(
      tap((_) => this.log('fetched storeProducts')),
      catchError(this.handleError<StoreProduct[]>('getStoreProducts', []))
    );
  }

  /** GET hero by id. Return `undefined` when id not found */
  getStoreProductNO404<Data>(id: number): Observable<StoreProduct> {
    const url = `${this.storeProductsUrl}/${id}`;
    return this.http.get<StoreProduct[]>(url).pipe(
      map((storeProducts) => storeProducts[0]), // returns a {0|1} element array
      tap((h) => {
        const outcome = h ? 'fetched' : 'did not find';
        this.log('${outcome} storeProduct id=${id}');
      }),
      catchError(this.handleError<StoreProduct>(`getStoreProduct id = ${id}`))
    );
  }

  /** GET hero by id. Will 404 if id not found */
  getStoreProduct(id: number): Observable<StoreProduct> {
    const url = `${this.storeProductsUrl}/${id}`;
    return this.http.get<StoreProduct>(url).pipe(
      tap((_) => this.log(`fetched storeProduct id=${id})`)),
      catchError(this.handleError<StoreProduct>(`getStoreProduct id = ${id}`))
    );
  }

  /* GET storeProducts whose name contains search term */
  searchStoreproducts(term: string): Observable<StoreProduct[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http
      .get<StoreProduct[]>(`${this.storeProductsUrl}/?name=${term}`)
      .pipe(
        tap((x) =>
          x.length
            ? this.log(`found storeProducts matching "${term}"`)
            : this.log(`no storeProducts matching "${term}"`)
        ),
        catchError(this.handleError<StoreProduct[]>('searchStoreproducts', []))
      );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addStoreProduct(storeProduct: StoreProduct): Observable<StoreProduct> {
    return this.http
      .post<StoreProduct>(this.storeProductsUrl, storeProduct, this.httpOptions)
      .pipe(
        tap((newStoreProduct: StoreProduct) =>
          this.log(
            `added storeProduct w/ id=${newStoreProduct.id}, name=${newStoreProduct.name}, price=${newStoreProduct.price}, quantity=${newStoreProduct.quantity}`
          )
        ),
        catchError(this.handleError<StoreProduct>('addStoreProduct'))
      );
  }

  /** DELETE: delete the hero from the server */
  deleteStoreProduct(id: number): Observable<StoreProduct> {
    const url = `${this.storeProductsUrl}/${id}`;

    return this.http.delete<StoreProduct>(url, this.httpOptions).pipe(
      tap((_) => this.log(`deleted storeProduct id=${id}`)),
      catchError(this.handleError<StoreProduct>('deleteStoreProduct'))
    );
  }

  /** PUT: update the hero on the server */
  updateStoreProduct(storeProduct: StoreProduct): Observable<any> {
    console.log("update called");
    console.log(storeProduct);
    return this.http
      .put(this.storeProductsUrl, storeProduct, this.httpOptions)
      .pipe(
        tap((_) => this.log(`updated storeProduct id=${storeProduct.id}`)),
        catchError(this.handleError<any>('updateStoreProduct'))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }
}
