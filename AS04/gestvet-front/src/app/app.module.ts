import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { AuthGuardService } from './services/auth-guard.service';
import { TokenStorage } from './services/token.storage';
import { AnimalsService } from './services/animals/animals.service';
import { AnimalComponent } from './funcionalidades/crud/animal/animal.component';
import { DrugComponent } from './funcionalidades/crud/drug/drug.component';
import { DrugService } from './services/drugs/drug.service';
import { NotifyService } from './services/notify/notify.service';
import { Messages } from '../app/messages/messages';
import { WindowRef } from './WindowRef';

const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuardService]
  },
  { path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'user/animal',
    component: AnimalComponent
  },
  {
    path: 'user/drug',
    component: DrugComponent
  },
  {
    path: '**',
    component: LoginComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    AnimalComponent,
    DrugComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, {useHash: true}),
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, AuthGuardService, TokenStorage, AnimalsService, DrugService, Messages, WindowRef, NotifyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
