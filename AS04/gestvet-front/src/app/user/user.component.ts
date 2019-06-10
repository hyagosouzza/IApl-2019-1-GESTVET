import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../services/token.storage';
import { AnimaisService } from '../services/animais.service';
import { Animal } from '../models/animal.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  animais: Array<Animal> = new Array();
  user: any;

  constructor(private token: TokenStorage, private animaisService: AnimaisService, private authService: AuthService) {
    authService.getCurrentUser().toPromise().then(user => {
      this.user = user;
    });
  }

  ngOnInit() {
  }

  getAnimais() {
    this.animaisService.getAnimais().toPromise().then( animais => {
      this.animais = animais;
    });
  }

  logout() {
    this.token.signOut();
  }

}
