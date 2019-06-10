import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../services/token.storage';
import { AnimaisService } from '../services/animais.service';
import { Animal } from '../models/animal.model';
import { AuthService } from '../services/auth.service';
import { NotifyService } from '../services/notify/notify.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  animais: Array<Animal> = new Array();
  user: any;

  constructor(private token: TokenStorage, private animaisService: AnimaisService, private authService: AuthService, private notifyService: NotifyService, private router: Router) {
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
    this.router.navigate(['login']);
    this.notifyService.createNotify("Sucesso", "Sessão Encerrada!", "green");
  }

}
