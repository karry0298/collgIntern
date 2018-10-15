import { Component, OnInit, Input  } from '@angular/core';
import { Patient } from '../patient';
import { DoctorUIDService } from "../doctor-uid.service";


@Component({
  selector: 'app-doctor-patientview',
  templateUrl: './doctor-patientview.component.html',
  styleUrls: ['./doctor-patientview.component.scss']
})
export class DoctorPatientviewComponent implements OnInit {
  @Input() patient: Patient;
  doctorDetails:string;

  isOn=1;

  check(): void{
  console.log(this.patient);
  }


  constructor(private data: DoctorUIDService) {
    this.doctorDetails=this.data.currentUser();
    console.log(this.doctorDetails);
     }

  ngOnInit() {

  }
  onSelect(hero: Patient): void {

    this.selectedPatient = hero;
    // console.log(this.selectedPatient);

    var doctorUid = this.getUid();
    var patientUid = this.selectedPatient.key;
    console.log(doctorUid);
    console.log(patientUid);

    // const id = this.selectedPatient.;
    // this.ref = this.afStorage.ref(id);
  }


}
