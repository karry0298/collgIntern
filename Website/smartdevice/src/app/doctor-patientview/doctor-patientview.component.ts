import { Component, OnInit, Input  } from '@angular/core';
import { Patient } from '../patient';

@Component({
  selector: 'app-doctor-patientview',
  templateUrl: './doctor-patientview.component.html',
  styleUrls: ['./doctor-patientview.component.scss']
})
export class DoctorPatientviewComponent implements OnInit {
  @Input() patient: Patient;
  isOn=1;

  check(): void{
  console.log(this.patient);  
  }
  constructor() { }

  ngOnInit() {

  }

}
