import { Component, OnInit } from '@angular/core';
import { Animal } from '../../../../models/animal.model';
import { AnimaisService} from '../../../../services/animais.service';

@Component({
  selector: 'app-criar-animal',
  templateUrl: './criar-animal.component.html',
  styleUrls: ['./criar-animal.component.scss'],
  providers: [AnimaisService]
})
export class CriarAnimalComponent implements OnInit {

  animal: Animal = new Animal();

  constructor(private animaisService: AnimaisService) { }

  ngOnInit() {
  }

  createAnimal(): void {
    if(this.animal.idade == null ||
      this.animal.nome == (null || '') ||
      this.animal.raca == (null || '') ||
      this.animal.tipo == (null || '') ) {
      alert('Todos os campos devem ser preenchidos');
      return;
    }

    this.animaisService.createAnimal(this.animal)
      .subscribe(data => {
        alert("Animal cadastrado com sucesso.");
        (document.getElementById("formAnimal") as HTMLFormElement).reset();
      });

  }

}
