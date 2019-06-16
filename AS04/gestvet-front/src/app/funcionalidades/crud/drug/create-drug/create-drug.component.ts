import { Component, OnInit } from '@angular/core';
import { Drug } from '../../../../models/drug.model';
import { DrugService } from '../../../../services/drug.service';
import { Messages } from '../../../../messages/messages';
import { WindowRef } from '../../../../WindowRef';

@Component({
  selector: 'app-create-drug',
  templateUrl: './create-drug.component.html',
  styleUrls: ['./create-drug.component.scss']
})
export class CreateDrugComponent implements OnInit {

  drug: Drug = new Drug();
  labels: {};

  constructor(private drugService: DrugService, private messages: Messages, private winRef: WindowRef) { }

  ngOnInit() {
    this.selectLanguage();
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
