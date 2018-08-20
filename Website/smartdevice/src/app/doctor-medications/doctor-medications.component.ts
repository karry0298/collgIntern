import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  @Input() patient: Patient;
display:string="none";

  openModal(): void{
    this.display="block";
  }
  closeModal(): void{
    this.display="none";
  }
  
  onSelect(hero: Patient): void {
    console.log(hero.medicines);
  }

  // onSelect(hero: Patient): void {
  //   this.selectedPatient = hero;
  //   console.log(this.selectedPatient);
  // }
  constructor() { }
  // display="none";
  //   let isDone: boolean = false; }

  ngOnInit() {
    console.log(this.patient.medicines);
  }

}
