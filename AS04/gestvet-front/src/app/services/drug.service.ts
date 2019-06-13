import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Drug} from '../models/drug.model';
import { TokenStorage } from './token.storage';

@Injectable()
export class DrugService {

  baseUrl = 'http://localhost:8080/api/medicamentos/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  public getDrugs() {
    return this.http.get<Drug[]>(this.baseUrl, { headers: this.headers } );
  }

  public createDrug(drug) {
    return this.http.post<Drug>(this.baseUrl, drug, { headers: this.headers });
  }

  public findOne(drug) {
    return this.http.get<Drug>(this.baseUrl + drug.id, { headers: this.headers });
  }

  public putDrug(drug) {
    return this.http.put(this.baseUrl + drug.id, drug, { headers: this.headers });
  }

  public deleteDrug(drug) {
    return this.http.delete(this.baseUrl + drug.id, { headers: this.headers });
  }

}
