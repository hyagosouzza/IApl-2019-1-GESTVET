import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TokenStorage } from '../services/token.storage';
import { NotifyService } from '../services/notify/notify.service';
import { Messages } from '../messages/messages';
import { WindowRef } from '../WindowRef';

interface token {
  accessToken: string,
  tokenType: string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {

  labels = {};

  constructor(private router: Router, private authService: AuthService, private token: TokenStorage,
    private messages: Messages, private winRef: WindowRef, private notifyService: NotifyService) {
    if (token.getToken() != undefined) {
      router.navigate(["/user"]);
    }
  }

  username: string;
  password: string;

  ngOnInit() {
    this.selectLanguage();
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).toPromise().then(
      data => {
        this.token.saveToken((data as token).accessToken);
        this.router.navigate(['user']);
        this.notifyService.createNotify('Sucesso', 'Autenticação realizada!', "green");
      }
    );
  }

  selectLanguage() {
    var country = this.winRef.nativeWindow.navigator.language.substring(3, 5)
    if (country === 'BR') {
      this.labels = this.messages.messages.pt;
    } else if (country === 'US') {
      this.labels = this.messages.messages.en;
    } else if (country === 'ES') {
      this.labels = this.messages.messages.es;
    }
  }

}