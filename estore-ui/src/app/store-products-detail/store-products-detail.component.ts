import { Component, OnInit, Input } from '@angular/core';
import { StoreProduct } from '../store-product';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

import {
  DomSanitizer,
  provideProtractorTestingSupport,
} from '@angular/platform-browser';

import { StoreProductServiceService } from '../store-product-service.service';
import { ShoppingCartService } from '../shopping-cart.service';
import { UserService } from '../user.service';
@Component({
  selector: 'app-store-products-detail',
  templateUrl: './store-products-detail.component.html',
  styleUrls: ['./store-products-detail.component.scss'],
})
export class StoreProductsDetailComponent implements OnInit {
  // storeProduct: StoreProduct | undefined;

  @Input() storeProduct?: StoreProduct;

  pendingImgUpload: boolean = false;

  apparelMockups: any = {
    bodysuit: {
      src: 'assets/img/mockups/bodysuit.png',
      inset: '36%',
      top: '5rem',
      sizes: ['S', 'M', 'L'],
    },
    hoodie: {
      src: 'assets/img/mockups/hoodie.png',
      inset: '37%',
      top: '8rem',
      sizes: ['S', 'M', 'L'],
    },
    infant_t_romper: {
      src: 'assets/img/mockups/infant_t_romper.png',
      inset: '35%',
      top: '6rem',
      sizes: ['S', 'M', 'L'],
    },
    jersey_tank: {
      src: 'assets/img/mockups/jersey_tank.png',
      inset: '30%',
      top: '9rem',
      sizes: ['S', 'M', 'L'],
    },
    long_sleeve: {
      src: 'assets/img/mockups/long_sleeve.png',
      inset: '31%',
      top: '5rem',
      sizes: ['S', 'M', 'L'],
    },
    sweater: {
      src: 'assets/img/mockups/sweater.png',
      inset: '32%',
      top: '6rem',
      sizes: ['S', 'M', 'L'],
    },
    't-shirt': {
      src: 'assets/img/mockups/tshirt.png',
      inset: '30%',
      top: '4.5rem',
      sizes: ['XS', 'S', 'M', 'L', 'XL', 'XXL'],
    },
  };

  productColor: string = '';
  productSize: string = '';
  customPrint: string = '';
  mainPicture: string = 'assets/img/tshirt.jpg';
  base64CustomPrint: any = '';

  customPrintList: string[] = [
    '',
    'assets/img/print_01.svg',
    'assets/img/print_02.svg',
    'assets/img/print_03.svg',
    'assets/img/print_04.svg',
    'assets/img/print_05.svg',
    'assets/img/print_06.svg',
    'assets/img/print_07.svg',
  ];

  colorCodes: any = {
    White: '#ffffff',
    YellowHaze: '#ecd18c',
    Violet: '#a1a2c0',
    TexasOrange: '#b05230',
    Lime: '#b2c76a',
    Kelly: '#26a383',
    Gold: '#e8b43e',
    Tangerine: '#e79238',
    Tan: '#cda977',
    StoneBlue: '#8ca8b4',
    SteelGreen: '#5f827b',
    SportsGrey: '#abadb9',
    Sky: '#8ab7d6',
    Marron: '#84284d',
    Kiwi: '#adbf73',
    HeatherForrest: '#46564b',
    Leaf: '#537871',
    SereneGreen: '#b6b295',
    Sapphire: '#0e8dba',
    Sand: '#d5cab8',
    Salmon: '#e9baaa',
    SafetyOrange: '#e89339',
    Royal: '#0c66a4',
    MetroBlue: '#165082',
    Cinnamon: '#b26249',
    Cardinal: '#98292f',
    Eggplant: '#592b4f',
    Red: '#df183f',
    Purple: '#422d88',
    PrairieDust: '#ac9069',
    Pistachio: '#adbd7e',
    Pine: '#767853',
    Paprika: '#e2465b',
    LightBlue: '#adc7d6',
    ForrestGreen: '#374d36',
    DarkHeath: '#647a88',
    Orchid: '#aaa7c6',
    Orange: '#e8a68c',
    Olive: '#777356',
    Oceano: '#7995a0',
    Navy: '#213051',
    LightPink: '#e6d1d0',
    DarkChocolate: '#44291e',
    Daisy: '#f3ca32',
    Jade: '#079a8a',
    IrishGreen: '#59a55a',
    IndigoBlue: '#4494c9',
    IceGrey: '#b9b8b3',
    Honey: '#e9cf7b',
    Heliconia: '#d12977',
    HeatherNavy: '#3a5369',
    Chestnut: '#7d4137',
    Charcoal: '#6f7983',
    Cedar: '#c8564c',
    CarolinaBlue: '#739fc2',
    Camel: '#e9d9ca',
    BlueDusk: '#4a5978',
    Black: '#323d3f',
    Azalea: '#e08da9',
    Avocado: '#d5ce7d',
  };

  description: string = `Lorem ipsum dolor sit amet consectetur adipisicing elit. Ut
  repellendus, quo, facilis explicabo modi magnam ea illum, assumenda
  suscipit voluptatem sint culpa quibusdam distinctio debitis
  exercitationem atque velit tenetur illo.`;

  constructor(
    private router: Router,
    //holds information about the route to this instance of the storeProductDetailComponent
    private route: ActivatedRoute,
    //gets storeproduct data from the remote server and this component uses it to get the storeproduct-to-display.
    private storeProductService: StoreProductServiceService,
    //  Angular service for interacting with the browser
    private location: Location,

    private shoppingCartService: ShoppingCartService,

    private sanitizer: DomSanitizer,

    public userService: UserService
  ) {}

  ngOnInit(): void {
    this.getStoreProduct();
    console.log('user: ' + this.userService.userID);
  }

  toDataURL(url: string, callback: any): void {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
      var reader = new FileReader();
      reader.onloadend = function () {
        callback(reader.result);
      };
      reader.readAsDataURL(xhr.response);
    };
    xhr.open('GET', url);
    xhr.responseType = 'blob';
    xhr.send();
  }

  getBase64StringFromDataURL(dataURL: any): any {
    return dataURL.replace('data:', '').replace(/^.+,/, '');
  }

  getBase64(i: number): void {
    let id: string = 'custom-print-' + i;
    const image: any = document.getElementById(id);
    fetch(image.src)
      .then((res) => res.blob())
      .then((blob) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          this.base64CustomPrint = reader.result;
        };
        reader.readAsDataURL(blob);
      });
  }

  setupImgUpload(): void {
    this.pendingImgUpload = true;
  }

  // https://stackoverflow.com/a/37432961/888094
  sanitize(url: string): any {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  // https://stackoverflow.com/a/51760964/888094
  fakeFileUpload(event: any): void {
    let img: any = new Image();
    img.onload = () => {
      let url: string = this.sanitize(img.src);
      this.customPrintList.push(url);
    };
    img.onerror = () => {};
    img.src = URL.createObjectURL(event.srcElement.files[0]);
    this.pendingImgUpload = false;
  }

  getStoreProduct(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.storeProductService.getStoreProduct(id).subscribe((storeProduct) => {
      this.storeProduct = storeProduct;
      this.productColor = '#ffffff';
      this.productSize = this.apparelMockups[this.storeProduct.type].sizes[0];
    });
  }

  goBack(): void {
    this.location.back();
  }
  //productID: productID, color: color, size: size, image: image, userID:userID
  purchase(color: String, size: String, image: String, userID: Number): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    // if(this.storeProduct?.quantity && this.storeProduct.quantity <= 0) {
    //   return;
    // }
    this.shoppingCartService
      .addProduct(id, color, size, image, userID)
      .subscribe(() => {
        this.shoppingCartService.getcart(userID);
        this.router.navigate(['shopping-cart']);
      });
  }
}
