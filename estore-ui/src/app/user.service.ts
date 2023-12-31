import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor() {}

  private userSource = new BehaviorSubject<User>({ id: -1, name: '' });
  currentUser = this.userSource.asObservable();
  userID = -1;

  changeUser(name: String) {
    let id: number = this.getUserId(name);
    let newUser: User = { id, name };
    this.userSource.next(newUser);
    this.userID = id;
  }

  // hash function from https://stackoverflow.com/a/15710692/
  getUserId(name: String): number {
    return name.split('').reduce(function (a, b) {
      a = (a << 5) - a + b.charCodeAt(0);
      return a & a;
    }, 0);
  }
}
