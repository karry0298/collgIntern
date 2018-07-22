import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  @Input() patient: Patient;
  constructor() { }
  // display='none';
  // onCloseHandled(){
  //
  //       this.display='none';
  //
  //    }
  //  openModal(){
  //
  //       this.display=block;
  //
  //  }

  openMod(){
    this.display=block;
  }

  cloaseMod(){
    this.display=none;
  }
isActive=true;


  ngOnInit() {
  }

}
