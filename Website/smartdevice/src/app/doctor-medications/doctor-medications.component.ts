import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  @Input() patient: Patient;
display:string="block";
 isDone: boolean = false;
  checkModal(): void{
    this.isDone=!isDone
    if(isDone)
      this.display="block";
    else
      this.display="none";

  }

  // onSelect(hero: Patient): void {
  //   this.selectedPatient = hero;
  //   console.log(this.selectedPatient);
  // }
  constructor() { }
  // display="none";
  //   let isDone: boolean = false; }

  ngOnInit() {

  }

}
