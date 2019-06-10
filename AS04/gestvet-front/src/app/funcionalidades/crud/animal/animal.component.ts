import { Component, OnInit } from '@angular/core';
import { AnimaisService} from '../../../services/animais.service';
import { Animal} from '../../../models/animal.model';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.scss']
})
export class AnimalComponent implements OnInit {

  animais: Animal[];
  animal: Animal = new Animal();
  animalUpdate: Animal = new Animal();
  findOneById: any;

  constructor(private animalService: AnimaisService) { }

  ngOnInit() {
    this.animalService.getAnimais()
      .subscribe(data => {
        this.animais = data;
    });
  }

  findOne(animal: Animal): void {
    this.animalService.findOne(animal)
      .subscribe(data => {
        this.findOneById = data;
        this.animalUpdate = this.findOneById;
      });
  }

  updateAnimal(): void {

    console.log(this.animalUpdate);
    this.animalService.updateAnimal(this.animalUpdate)
      .subscribe(data => {
        alert('Animal Editado!');
        location.reload();
      });
  }

  deletarAnimal(animal: Animal): void {

    this.animalService.deleteAnimal(animal)
      .subscribe(data => {
        this.animais = this.animais.filter(u => u !== animal);
      });
  }

}
