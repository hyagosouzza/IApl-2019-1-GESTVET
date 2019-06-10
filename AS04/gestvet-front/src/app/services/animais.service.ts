import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Animal } from '../models/animal.model';
import { TokenStorage } from './token.storage';

@Injectable()
export class AnimaisService {

  baseUrl = 'http://localhost:8080/api/animais/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  getAnimais() {
    return this.http.get<Animal[]>(this.baseUrl, { headers: this.headers });
  }

  createAnimal(animal) {
    return this.http.post<Animal>(this.baseUrl, animal, { headers: this.headers });
  }

  public findOne(animal) {
    return this.http.get<Animal>(this.baseUrl + animal.id, { headers: this.headers });
  }

  public updateAnimal(animal) {
    return this.http.put(this.baseUrl + animal.id, animal, { headers: this.headers });
  }

  public deleteAnimal(animal) {
    return this.http.delete(this.baseUrl + animal.id, { headers: this.headers });
  }

}
