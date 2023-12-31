import { Component } from '@angular/core';

import { ProductForm } from '../productForm';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent {

  price= [5,10,20,30,40,50,60]

  model = new ProductForm(18,'name', this.price[0], 20);

  submitted = false;

  onSubmit() {this.submitted=true;}

  newProductForm(){
    this.model = new ProductForm(18,'', 0,0)
  }



}
