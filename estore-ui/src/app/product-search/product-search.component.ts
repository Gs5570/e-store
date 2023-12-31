import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';

import { StoreProduct } from '../store-product';
import { StoreProductServiceService } from '../store-product-service.service';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.scss'],
})
export class ProductSearchComponent implements OnInit {
  storeProducts$!: Observable<StoreProduct[]>;

  private searchTerms = new Subject<string>();
  
  constructor(private storeProductService: StoreProductServiceService) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.storeProducts$ = this.searchTerms.pipe(
      //waitwait 300ms after each keystroke before considering the term
      debounceTime(300),

      //ignore new term if same as previous term
      distinctUntilChanged(),

      //switch to new search observable each time the term changes
      switchMap((term: string) =>
        this.storeProductService.searchStoreproducts(term)
      )
    );

  }
}
