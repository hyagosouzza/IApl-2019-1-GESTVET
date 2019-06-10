import { Component, OnInit } from '@angular/core';
import {Medicamento} from '../../../../models/medicamento.model';
import {MedicamentoService} from '../../../../services/medicamento.service';

@Component({
  selector: 'app-criar-medicamento',
  templateUrl: './criar-medicamento.component.html',
  styleUrls: ['./criar-medicamento.component.scss']
})
export class CriarMedicamentoComponent implements OnInit {

  medicamento: Medicamento = new Medicamento();

  constructor(private medicamentoService: MedicamentoService) { }

  ngOnInit() {
  }

  createMedicamento(): void {
    if (this.medicamento.nome === (null) ||
      this.medicamento.preco === (null) ||
      this.medicamento.dosagem === (null) ||
      this.medicamento.quantidade === (null) ) {
      alert('Todos os campos devem ser preenchidos');
      return;
    }

    this.medicamentoService.createMedicamento(this.medicamento)
      .subscribe(data => {
        alert('Medicamento cadastrado com sucesso.');
      });

  }

}
