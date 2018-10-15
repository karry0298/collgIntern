import { Component, OnInit, Input ,OnChanges  } from '@angular/core';
import { Patient } from '../patient';
import { DoctorUIDService } from "../doctor-uid.service";
import { SimpleChanges } from '@angular/core';


@Component({
  selector: 'app-doctor-patientview',
  templateUrl: './doctor-patientview.component.html',
  styleUrls: ['./doctor-patientview.component.scss']
})
export class DoctorPatientviewComponent implements OnInit,OnChanges {
  @Input() patient: Patient;
  doctorDetails:string;

  isOn=1;

  check(): void{
  console.log(this.patient);
  }


  constructor(private data: DoctorUIDService) {
    this.doctorDetails=this.data.currentUser();
    //console.log(this.patient);
  }

  ngOnChanges(changes: SimpleChanges) {

    console.log(changes.patient.previousValue);
    if(changes.patient.previousValue){
      console.log(this.patient.imp);

    }
    // You can also use categoryId.previousValue and 
    // categoryId.firstChange for comparing old and new values

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
