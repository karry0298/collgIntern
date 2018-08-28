import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
import { Medecine } from '../medecine';
import * as firebase from 'firebase/app';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  @Input() patient: Patient;
  medicine:Medecine;
  brandName:String;
  
  dateTime:Date;
  // str1:String="";str2:String="";str3:String="";str4:String="";
  meds:string[];

display:string="none";

  openModal(): void{
    this.display="block";
  }
  closeModal(): void{
    this.display="none";
  }
  
  submitMedsForm():any{
    this.medicine.brandName=this.brandName;
    this.medicine.dateStarted=this.dateTime;
    for(var  i=0;i<4;i++){
      if(this.meds[i]!=null){
        this.medicine.consumptionTimings.push(this.meds[i]);
      }
    }
    this.medicine.prescriptionBy="Dr."+
  } 
  onSelect(hero: Patient): void {
    console.log(hero.medicines);
  }

  // onSelect(hero: Patient): void {
  //   this.selectedPatient = hero;
  //   console.log(this.selectedPatient);
  // }
  constructor() {
    
   }
  // display="none";
  //   let isDone: boolean = false; }

  ngOnInit() {
    console.log(this.patient.medicines);
  }

}
