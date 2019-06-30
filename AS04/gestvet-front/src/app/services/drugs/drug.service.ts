import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Drug} from '../../models/drug.model';
import { TokenStorage } from '../token.storage';

@Injectable()
export class DrugService {

  baseDrugsUrl = 'http://localhost:8080/gestvet/drugs/';
  baseDrugUrl = 'http://localhost:8080/gestvet/drugs/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  public getDrugs() {
    return this.http.get<Drug[]>(this.baseDrugsUrl, { headers: this.headers } );
  }

  public createDrug(drug) {
    return this.http.post<Drug>(this.baseDrugUrl, drug, { headers: this.headers });
  }

  public findOne(drug) {
    return this.http.get<Drug>(this.baseDrugUrl + drug.id, { headers: this.headers });
  }

  public putDrug(drug) {
    return this.http.put(this.baseDrugUrl + drug.id, drug, { headers: this.headers });
  }

  public deleteDrug(drug) {
    return this.http.delete(this.baseDrugUrl + drug.id, { headers: this.headers });
  }

}
