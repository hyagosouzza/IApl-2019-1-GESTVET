import {Component, OnInit} from '@angular/core';
import {Drug} from '../../../models/drug.model';
import {DrugService} from '../../../services/drugs/drug.service';
import {Messages} from '../../../messages/messages';
import {WindowRef} from '../../../WindowRef';
import {NotifyService} from '../../../services/notify/notify.service';

declare let $: any;

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
  labels: any;

  constructor(private drugService: DrugService, private messages: Messages, private winRef: WindowRef, private notifyService: NotifyService) {
  }

  ngOnInit() {
    this.initData();
    this.selectLanguage();
    $('.price').mask('00000,00');
    $('.quantity').mask('0#');
  }

  initData() {
    this.drugService.getDrugs().subscribe(
      data => this.drugs = data
    );
  }

  findOne(drug: Drug): void {
    this.drugService.findOne(drug).subscribe(
      data => this.updateDrug = this.findOneById = data
    );
  }

  checkFields() {

    if (this.drug.name == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createDrugName, 'orange');
      return false;
    }
    if (this.drug.price == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createDrugPrice, 'orange');
      return false;
    }
    if (this.drug.dosage == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createDrugDosage, 'orange');
      return false;
    }
    if (this.drug.quantity == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createDrugQuantity, 'orange');
      return false;
    }
    this.drug.quantity = Number.parseInt(this.drug.quantity.toString());
    this.drug.price = Number.parseFloat(this.drug.price.toString());
    return true;
  }

  createDrug(): void {
    if (!this.checkFields()) {
      return;
    }

    this.drugService.createDrug(this.drug).toPromise().then(
      retorno => {
        this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.createDrug, 'green');
        (document.getElementById('createDrug') as HTMLFormElement).reset();
        this.initData();
        $('.modal').modal('hide');
      }
    ).catch(erro => {
      this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.createDrugError, 'red');
      (document.getElementById('createDrug') as HTMLFormElement).reset();
      this.initData();
      $('.modal').modal('hide');
    });
  }

  putDrug(): void {
    this.drugService.putDrug(this.updateDrug).toPromise().then(retorno => {
      this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.editDrug, 'green');
      this.initData();
      $('.modal').modal('hide');
    }).catch(erro => {
      this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.editDrugError, 'red');
      this.initData();
      $('.modal').modal('hide');
    });
  }

  deleteDrug(drug: Drug): void {
    this.drugService.deleteDrug(drug).toPromise().then(retorno => {
      this.drugs = this.drugs.filter(u => u !== drug);
      this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.deleteDrug, 'green');
    }).catch(erro => {
      this.drugs = this.drugs.filter(u => u !== drug);
      this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.deleteDrugError, 'red');
    });
  }

  selectLanguage() {
    var country = this.winRef.nativeWindow.navigator.language.substring(3, 5);
    if (country === 'BR') {
      this.labels = this.messages.messages.pt;
    } else if (country === 'US') {
      this.labels = this.messages.messages.en;
    } else if (country === 'ES') {
      this.labels = this.messages.messages.es;
    }
  }
}
