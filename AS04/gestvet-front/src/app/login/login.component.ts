import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TokenStorage } from '../services/token.storage';
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
              private messages: Messages, private winRef: WindowRef) {
    if(token.getToken() != undefined) {
      router.navigate(["/user"]);
    }
  }

  username: string;
  password: string;

  ngOnInit(){
    this.selectLanguage();
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).toPromise().then(
      data => {
        this.token.saveToken((data as token).accessToken);
        this.router.navigate(['user']);
      }
    );
  }

  selectLanguage() {
    var country = this.winRef.nativeWindow.navigator.language.substring(3,5)
    var messages = this.messages.getMessages();
    if (country === 'BR'){
      this.labels = messages.pt;
    } else if (country === 'US'){
      this.labels = messages.en
    } else if (country === 'ES'){
      this.labels = messages.es
    }
  }

}