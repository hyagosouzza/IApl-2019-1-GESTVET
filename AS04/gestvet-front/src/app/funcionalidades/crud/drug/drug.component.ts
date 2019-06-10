import { Component, OnInit } from '@angular/core';
import {Drug} from '../../../models/drug.model';
import {DrugService} from '../../../services/drug.service';

@Component({
  selector: 'app-drug',
  templateUrl: './drug.component.html',
  styleUrls: ['./drug.component.scss']
})
export class DrugComponent implements OnInit {

  drugs: Drug[];
  drug: Drug = new Drug();
  updateDrug: Drug = new Drug();
  findOneById: any;

  constructor(private drugService: DrugService) { }

  ngOnInit() {
    this.drugService.getDrugs().subscribe(
      data => this.drugs = data
    );
  }

  findOne(drug: Drug): void {
    this.drugService.findOne(drug).subscribe(
      data => this.updateDrug = this.findOneById = data
    );
  }

  putDrug(): void {
    this.drugService.putDrug(this.updateDrug).subscribe(
      data => {
        alert('Medicamento editado');
        location.reload();
      }
    );
  }

  deleteDrug(drug: Drug): void {
    this.drugService.deleteDrug(drug).subscribe(
      data => this.drugs = this.drugs.filter(u => u !== drug)
    );
  }
}
