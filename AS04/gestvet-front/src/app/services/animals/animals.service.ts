import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Animal } from '../../models/animal.model';
import { TokenStorage } from '../token.storage';
import {NotifyService} from '../notify/notify.service';

@Injectable()
export class AnimalsService {

  baseUrl = 'http://localhost:8080/gestvet/animals/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage, private notifyService: NotifyService) {
  }

  public getAnimals() {
    return this.http.get<Animal[]>(this.baseUrl, { headers: this.headers });
  }

  public createAnimal(animal) {
    try {
      return this.http.post<Animal>(this.baseUrl, animal, { headers: this.headers });
    } catch (e) {
      return e;
    }
  }

  public findOne(animal) {
    return this.http.get<Animal>(this.baseUrl + animal.id, { headers: this.headers });
  }

  public putAnimal(animal) {
    return this.http.put(this.baseUrl + animal.id, animal, { headers: this.headers });
  }

  public deleteAnimal(animal) {
    return this.http.delete(this.baseUrl + animal.id, { headers: this.headers });
  }

}
