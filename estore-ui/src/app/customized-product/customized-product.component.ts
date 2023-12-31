import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-customized-product',
  templateUrl: './customized-product.component.html',
  styleUrls: ['./customized-product.component.scss'],
})
export class CustomizedProductComponent implements OnInit {
  apparelMockups: any = {
    bodysuit: {
      src: 'assets/img/mockups/bodysuit.png',
      inset: '36%',
      top: '28%',
      sizes: ['S', 'M', 'L'],
    },
    hoodie: {
      src: 'assets/img/mockups/hoodie.png',
      inset: '37%',
      top: '44%',
      sizes: ['S', 'M', 'L'],
    },
    infant_t_romper: {
      src: 'assets/img/mockups/infant_t_romper.png',
      inset: '35%',
      top: '33%',
      sizes: ['S', 'M', 'L'],
    },
    jersey_tank: {
      src: 'assets/img/mockups/jersey_tank.png',
      inset: '30%',
      top: '39%',
      sizes: ['S', 'M', 'L'],
    },
    long_sleeve: {
      src: 'assets/img/mockups/long_sleeve.png',
      inset: '31%',
      top: '28%',
      sizes: ['S', 'M', 'L'],
    },
    sweater: {
      src: 'assets/img/mockups/sweater.png',
      inset: '32%',
      top: '29%',
      sizes: ['S', 'M', 'L'],
    },
    't-shirt': {
      src: 'assets/img/mockups/tshirt.png',
      inset: '30%',
      top: '25%',
      sizes: ['XS', 'S', 'M', 'L', 'XL', 'XXL'],
    },
  };

  @Input() productType!: string;
  @Input() productColor!: string;
  @Input() productImage!: string;

  constructor(private sanitizer: DomSanitizer) {}

  imagePath(): any {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.productImage);
  }

  ngOnInit(): void {}
}
