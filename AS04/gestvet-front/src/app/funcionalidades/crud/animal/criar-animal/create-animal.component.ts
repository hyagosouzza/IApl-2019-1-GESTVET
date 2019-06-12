import { Component, OnInit } from '@angular/core';
import { Animal } from '../../../../models/animal.model';
import { AnimalsService} from '../../../../services/animals.service';

@Component({
  selector: 'app-create-animal',
  templateUrl: './create-animal.component.html',
  styleUrls: ['./create-animal.component.scss'],
  providers: [AnimalsService]
})
export class CreateAnimalComponent implements OnInit {

  animal: Animal = new Animal();

  constructor(private animalsService: AnimalsService) { }

  ngOnInit() {
  }

  createAnimal(): void {
    if(this.animal.age == null ||
      this.animal.name == (null || '') ||
      this.animal.breed == (null || '') ||
      this.animal.species == (null || '') ) {
      alert('Todos os campos devem ser preenchidos');
      return;
    }

    this.animalsService.createAnimal(this.animal)
      .subscribe(data => {
        alert("Animal cadastrado com sucesso.");
        (document.getElementById("formAnimal") as HTMLFormElement).reset();
      });

  }

}
