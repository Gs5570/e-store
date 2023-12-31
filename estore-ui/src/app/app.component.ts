import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { UserService } from './user.service';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'estore-ui';
  currentUser: User = { id: -1, name: '' };
  url: String = '';

  constructor(private router: Router, private userService: UserService) {
    router.events.subscribe((route) => {
      if (route instanceof NavigationEnd) {
        this.url = route.url;
        if (this.url && this.url.length > 0) {
          this.url = this.url.slice(1);
        }
      }
    });
  }

  signOut() {
    this.currentUser = { id: -1, name: '' };
    this.router.navigate(['sign-in']);
  }

  ngOnInit() {
    this.userService.currentUser.subscribe((user) => {
      this.currentUser = user;
      if (this.currentUser.name == 'admin') {
        this.router.navigate(['inventory']);
      } else {
        this.router.navigate(['products']);
      }
    });
    if (this.currentUser.name == '') {
      this.router.navigate(['sign-in']);
    }
  }
}
