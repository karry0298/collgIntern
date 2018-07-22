import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  @Input() patient: Patient;

    display:string='none';
    constructor() { }

openModal()
{
   this.display='block';
}

onCloseHandled()
{
   this.display='none';
}

  ngOnInit() {
  }

}
