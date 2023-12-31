import { Component, OnInit } from '@angular/core';

import { InventoryService } from '../inventory.service';
import { StoreProduct } from '../store-product';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.scss'],
})
export class InventoryComponent implements OnInit {
  unsavedChanges: boolean[] = [];

  productTypes: string[] = [
    'bodysuit',
    'hoodie',
    'infant_t_romper',
    'jersey_tank',
    'long_sleeve',
    'sweater',
    't-shirt',
  ];

  constructor(private inventoryService: InventoryService) {}

  inventory: StoreProduct[] = [];

  returnStoreProduct(i: number): StoreProduct {
    return {
      id: this.inventory[i].id,
      name: this.inventory[i].name,
      price: +this.inventory[i].price,
      quantity: +this.inventory[i].quantity,
      description: this.inventory[i].description,
      type: this.inventory[i].type,
      image: this.inventory[i].image,
    };
  }

  getInventory(first: boolean = false): void {
    this.inventoryService.getInventory().subscribe((inventory) => {
      this.inventory = inventory;
      if (first) {
        for (let i = 0; i < this.inventory.length; i++) {
          this.unsavedChanges.push(false);
        }
      }
    });
  }

  save(i: number): void {
    console.log(this.returnStoreProduct(i));
    if (
      (<HTMLInputElement>document.getElementById(`prod-name${i}`)).value ==
        '' ||
      (<HTMLInputElement>document.getElementById(`prod-price${i}`)).value ==
        '' ||
      (<HTMLInputElement>document.getElementById(`prod-quantity${i}`)).value ==
        ''
    ) {
      alert('Before saving, please fix the invalid fields.');
      return;
    }
    this.inventoryService
      .updateProduct(this.returnStoreProduct(i))
      .subscribe(() => {
        console.log('success!');
        this.unsavedChanges[i] = false;
      });
    return;
  }

  delete(i: number): void {
    if (confirm('Are you sure?')) {
      this.unsavedChanges.splice(i, 1);
      this.inventoryService
        .deleteProduct(this.returnStoreProduct(i))
        .subscribe(() => {
          this.getInventory();
        });
    }
  }

  new(): void {
    this.inventoryService.newProduct().subscribe(() => this.getInventory());
    this.unsavedChanges.push(false);
  }

  ngOnInit(): void {
    this.getInventory(true);
  }
}
