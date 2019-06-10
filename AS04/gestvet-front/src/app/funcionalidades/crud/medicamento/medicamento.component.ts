import { Component, OnInit } from '@angular/core';
import {Medicamento} from '../../../models/medicamento.model';
import {MedicamentoService} from '../../../services/medicamento.service';

@Component({
  selector: 'app-medicamento',
  templateUrl: './medicamento.component.html',
  styleUrls: ['./medicamento.component.scss']
})
export class MedicamentoComponent implements OnInit {

  medicamentos: Medicamento[];
  medicamento: Medicamento = new Medicamento();
  medicamentoUpdate: Medicamento = new Medicamento();
  findOneById: any;

  constructor(private medicamentoService: MedicamentoService) { }

  ngOnInit() {
    this.medicamentoService.getMedicamentos().subscribe(
      data => this.medicamentos = data
    );
  }

  findOne(medicamento: Medicamento): void {
    this.medicamentoService.findOne(medicamento).subscribe(
      data => this.medicamentoUpdate = this.findOneById = data
    );
  }

  updateMedicamento(): void {
    this.medicamentoService.updateMedicamento(this.medicamentoUpdate).subscribe(
      data => {
        alert('Medicamento editado');
        location.reload();
      }
    );
  }

  deletarMedicamento(medicamento: Medicamento): void {
    this.medicamentoService.deleteMedicamento(medicamento).subscribe(
      data => this.medicamentos = this.medicamentos.filter(u => u !== medicamento)
    );
  }
}
