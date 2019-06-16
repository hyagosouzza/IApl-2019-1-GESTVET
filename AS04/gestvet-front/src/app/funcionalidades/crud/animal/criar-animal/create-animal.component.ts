import { Component, OnInit } from '@angular/core';
import { Animal } from '../../../../models/animal.model';
import { AnimalsService} from '../../../../services/animals.service';
import { Messages } from '../../../../messages/messages';
import { WindowRef } from '../../../../WindowRef';

@Component({
  selector: 'app-create-animal',
  templateUrl: './create-animal.component.html',
  styleUrls: ['./create-animal.component.scss'],
  providers: [AnimalsService]
})
export class CreateAnimalComponent implements OnInit {

  labels = {};
  animal: Animal = new Animal();

  constructor(private animalsService: AnimalsService, private messages: Messages, private winRef: WindowRef) { }

  ngOnInit() {
    this.selectLanguage();
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
