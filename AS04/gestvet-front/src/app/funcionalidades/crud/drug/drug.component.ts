import { Component, OnInit } from '@angular/core';
import { Drug } from '../../../models/drug.model';
import { DrugService } from '../../../services/drug.service';
import { Messages } from '../../../messages/messages';
import { WindowRef } from '../../../WindowRef';

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
  labels: {};

  constructor(private drugService: DrugService, private messages: Messages, private winRef: WindowRef) { }

  ngOnInit() {
    this.drugService.getDrugs().subscribe(
      data => this.drugs = data
    );
    this.selectLanguage();
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

  selectLanguage() {
    var country = this.winRef.nativeWindow.navigator.language.substring(3,5)
    if (country === 'BR'){
      this.labels = this.messages.messages.pt;
    } else if (country === 'US'){
      this.labels = this.messages.messages.en;
    } else if (country === 'ES'){
      this.labels = this.messages.messages.es;
    }
  }
}
