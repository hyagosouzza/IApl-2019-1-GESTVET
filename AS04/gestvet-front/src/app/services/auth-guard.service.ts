import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { TokenStorage } from './token.storage';

@Injectable()
export class AuthGuardService implements CanActivate {

  private isAuthenticated: boolean = false;

  constructor(private token: TokenStorage) {
    if(token.getToken() != undefined) {
      this.isAuthenticated = true;
    } else {
      this.isAuthenticated = false;
    }
  }

  canActivate() {
    return this.isAuthenticated;
  }

}
