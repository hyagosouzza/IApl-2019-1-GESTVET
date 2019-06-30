import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Animal } from '../../models/animal.model';
import { TokenStorage } from '../token.storage';
import {NotifyService} from '../notify/notify.service';

@Injectable()
export class AnimalsService {

  baseAnimalsUrl = 'http://localhost:8080/gestvet/animals/';
  baseAnimalUrl = 'http://localhost:8080/gestvet/animal/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage, private notifyService: NotifyService) {
  }

  public getAnimals() {
    return this.http.get<Animal[]>(this.baseAnimalsUrl, { headers: this.headers });
  }

  public createAnimal(animal) {
    return this.http.post<Animal>(this.baseAnimalUrl, animal, { headers: this.headers });
  }

  public findOne(animal) {
    return this.http.get<Animal>(this.baseAnimalUrl + animal.id, { headers: this.headers });
  }

  public putAnimal(animal) {
    return this.http.put(this.baseAnimalUrl + animal.id, animal, { headers: this.headers });
  }

  public deleteAnimal(animal) {
    return this.http.delete(this.baseAnimalUrl + animal.id, { headers: this.headers });
  }

}
