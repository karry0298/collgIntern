import { Component, OnInit, Input  } from '@angular/core';
import { Patient } from '../patient';
import { DoctorUIDService } from "../doctor-uid.service";
import { Medecine } from '../medecine';


@Component({
  selector: 'app-doctor-patientview',
  templateUrl: './doctor-patientview.component.html',
  styleUrls: ['./doctor-patientview.component.scss']
})
export class DoctorPatientviewComponent implements OnInit {
  @Input() patient: Patient;
  doctorDetails:string;

  isOn=1;
  selectedMed:Medecine;
  check(): void{
  console.log(this.patient);
  }


  constructor(private data: DoctorUIDService) {
    this.doctorDetails=this.data.currentUser();
    console.log(this.doctorDetails);
     }

  ngOnInit() {

  }
  onSelect(hero: Medecine): void {

    this.selectedMed = hero;
    // console.log(this.selectedPatient);


    // const id = this.selectedPatient.;
    // this.ref = this.afStorage.ref(id);
  }


}
