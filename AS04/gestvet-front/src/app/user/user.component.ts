import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../services/token.storage';
import { AnimalsService } from '../services/animals.service';
import { Animal } from '../models/animal.model';
import { AuthService } from '../services/auth.service';
import { Messages } from '../messages/messages';
import { WindowRef } from '../WindowRef';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  animals: Array<Animal> = new Array();
  user: any;
  labels: {}

  constructor(private token: TokenStorage, private animalsService: AnimalsService, private authService: AuthService,
              private messages: Messages, private winRef: WindowRef) {
    authService.getCurrentUser().toPromise().then(user => {
      this.user = user;
    });
  }

  ngOnInit() {
    this.selectLanguage();
  }

  getAnimais() {
    this.animalsService.getAnimals().toPromise().then( animals => {
      this.animals = animals;
    });
  }

  logout() {
    this.token.signOut();
  }

  selectLanguage() {
    var country = this.winRef.nativeWindow.navigator.language.substring(3,5)
    if (country === 'BR'){
      this.labels = this.messages.messages.pt;
    } else if (country === 'US'){
      this.labels = this.messages.messages.en;
    } else if (country === 'ES'){
      this.labels = this.messages.messages.es;
    }
  }

}
