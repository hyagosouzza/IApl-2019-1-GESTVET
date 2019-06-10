import { Component, OnInit } from '@angular/core';
import {Drug} from '../../../../models/drug.model';
import {DrugService} from '../../../../services/drug.service';

@Component({
  selector: 'app-create-drug',
  templateUrl: './create-drug.component.html',
  styleUrls: ['./create-drug.component.scss']
})
export class CreateDrugComponent implements OnInit {

  drug: Drug = new Drug();

  constructor(private drugService: DrugService) { }

  ngOnInit() {
  }

  createDrug(): void {
    if (this.drug.name === (null) ||
      this.drug.price === (null) ||
      this.drug.dosage === (null) ||
      this.drug.quantity === (null) ) {
      alert('Todos os campos devem ser preenchidos');
      return;
    }

    this.drugService.createDrug(this.drug)
      .subscribe(data => {
        alert('Medicamento cadastrado com sucesso.');
      });

  }

}
