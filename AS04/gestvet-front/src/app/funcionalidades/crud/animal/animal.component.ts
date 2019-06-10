import { Component, OnInit } from '@angular/core';
import { AnimalsService} from '../../../services/animals.service';
import { Animal} from '../../../models/animal.model';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.scss']
})
export class AnimalComponent implements OnInit {

  animals: Animal[];
  animal: Animal = new Animal();
  updateAnimal: Animal = new Animal();
  findOneById: any;

  constructor(private animalService: AnimalsService) { }

  ngOnInit() {
    this.animalService.getAnimais()
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

  deletarAnimal(animal: Animal): void {

    this.animalService.deleteAnimal(animal)
      .subscribe(data => {
        this.animals = this.animals.filter(u => u !== animal);
      });
  }

}
