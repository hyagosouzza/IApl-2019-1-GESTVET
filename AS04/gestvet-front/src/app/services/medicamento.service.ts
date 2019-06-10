import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Medicamento} from '../models/medicamento.model';
import { TokenStorage } from './token.storage';

@Injectable()
export class MedicamentoService {

  baseUrl = 'http://localhost:8080/api/medicamentos/';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.token.getToken()
  })

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  getMedicamentos() {
    return this.http.get<Medicamento[]>(this.baseUrl, { headers: this.headers } );
  }

  createMedicamento(medicamento) {
    return this.http.post<Medicamento>(this.baseUrl, medicamento, { headers: this.headers });
  }

  public findOne(medicamento) {
    return this.http.get<Medicamento>(this.baseUrl + medicamento.id, { headers: this.headers });
  }

  public updateMedicamento(medicamento) {
    return this.http.put(this.baseUrl + medicamento.id, medicamento, { headers: this.headers });
  }

  public deleteMedicamento(medicamento) {
    return this.http.delete(this.baseUrl + medicamento.id, { headers: this.headers });
  }

}
