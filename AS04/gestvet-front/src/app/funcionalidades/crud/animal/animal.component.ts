import {Component, OnInit} from '@angular/core';
import {AnimalsService} from '../../../services/animals/animals.service';
import {Animal} from '../../../models/animal.model';
import {Messages} from '../../../messages/messages';
import {WindowRef} from '../../../WindowRef';
import {NotifyService} from '../../../services/notify/notify.service';

declare let $: any;

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.scss']
})
export class AnimalComponent implements OnInit {

  labels: any;
  animals: Animal[];
  animal: Animal = new Animal();
  updateAnimal: Animal = new Animal();
  findOneById: any;

  constructor(private animalService: AnimalsService, private messages: Messages, private winRef: WindowRef, private notifyService: NotifyService) {
  }

  ngOnInit() {
    this.selectLanguage();
    this.initData();
    $('.age').mask('0#');
  }

  initData() {
    this.animalService.getAnimals()
      .subscribe(data => {
        this.animals = data;
      });
  }

  findOne(animal: Animal): void {
    this.animalService.findOne(animal)
      .subscribe(data => {
        this.findOneById = data;
        this.updateAnimal = this.findOneById;
      });
  }

  putAnimal(): void {

    this.animalService.putAnimal(this.updateAnimal).toPromise().then(
      retorno => {
        this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.editAnimal, 'green');
        this.initData();
        $('.modal').modal('hide');
      }
    ).catch(erro => {
      this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.editAnimalError, 'red');
      this.initData();
      $('.modal').modal('hide');
    });
  }

  deleteAnimal(animal: Animal): void {

    this.animalService.deleteAnimal(animal).toPromise().then(
      retorno => {
        this.animals = this.animals.filter(u => u !== animal);
        this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.deleteAnimal, 'green');
      }
    ).catch(
      erro => {
        this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.deleteAnimalError, 'red');
      }
    );
  }

  checkFields() {

    if (this.animal.name == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createAnimalName, 'orange');
      return false;
    }
    if (this.animal.age == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createAnimalAge, 'orange');
      return false;
    }
    if (this.animal.breed == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createAnimalBreed, 'orange');
      return false;
    }
    if (this.animal.species == null || '') {
      this.notifyService.createNotify('Aviso', this.labels.notifications.createAnimalSpecies, 'orange');
      return false;
    }
    this.animal.age = Number.parseInt(this.animal.age.toString());
    return true;
  }

  createAnimal(): void {
    if (!this.checkFields()) {
      return;
    }

    this.animalService.createAnimal(this.animal).toPromise().then(
      retorno => {
        this.notifyService.createNotify(this.labels.notifications.success, this.labels.notifications.createAnimal, 'green');
        (document.getElementById('createAnimal') as HTMLFormElement).reset();
        this.initData();
        $('.modal').modal('hide');
      }
    ).catch(erro => {
      this.notifyService.createNotify(this.labels.notifications.warning, this.labels.notifications.createAnimalError, 'red');
      (document.getElementById('createAnimal') as HTMLFormElement).reset();
      this.initData();
      $('.modal').modal('hide');
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
