import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  constructor(private userService: UserService) {}

  userName: String = '';

  ngOnInit(): void {}

  newUser() {
    this.userService.changeUser(this.userName);
  }
}
