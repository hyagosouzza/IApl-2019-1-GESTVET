import { Component, OnInit } from '@angular/core';
import { AnimalsService} from '../../../services/animals.service';
import { Animal} from '../../../models/animal.model';
import { Messages } from '../../../messages/messages';
import { WindowRef } from '../../../WindowRef';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.scss']
})
export class AnimalComponent implements OnInit {

  labels = {};
  animals: Animal[];
  animal: Animal = new Animal();
  updateAnimal: Animal = new Animal();
  findOneById: any;

  constructor(private animalService: AnimalsService, private messages: Messages, private winRef: WindowRef) { }

  ngOnInit() {
    this.selectLanguage();
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

    console.log(this.updateAnimal);
    this.animalService.putAnimal(this.updateAnimal)
      .subscribe(data => {
        alert('Animal Editado!');
        location.reload();
      });
  }

  deleteAnimal(animal: Animal): void {

    this.animalService.deleteAnimal(animal)
      .subscribe(data => {
        this.animals = this.animals.filter(u => u !== animal);
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
