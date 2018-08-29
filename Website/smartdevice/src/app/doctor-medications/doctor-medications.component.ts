import { Component, OnInit ,Input} from '@angular/core';
import { Patient } from '../patient';
import { Medecine } from '../medecine';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit {
  constructor(private db: AngularFireDatabase) {
    
  }
  @Input() patient: Patient;
  medicine:Medecine=new Medecine();
  brandName:String="";
 
  auth:AngularFireAuth;
  
  dateTime:Date;
  // str1:String="";str2:String="";str3:String="";str4:String="";
  meds=[];
  

display:string="none";

  openModal(): void{
    this.display="block";
  }
  closeModal(): void{
    this.display="none";
  }
  
<<<<<<< refs/remotes/origin/Nicholas
  submitMedsForm():any{
    console.log(this.medicine);
    this.display="none";
    this.medicine.brandName=this.brandName;
    this.medicine.dateStarted=this.dateTime;
    for(var  i=0;i<4;i++){
      if(this.meds[i]!=null){
        console.log(this.meds[i]);
        console.log(this.medicine.consumptionTimings);
        this.medicine.consumptionTimings.push(this.meds[i]);
        
      }
    }
    this.medicine.prescriptionBy="Dr."+firebase.auth().currentUser.displayName;

  var ref = this.db.list('/wards/'+firebase.auth().currentUser.uid+'/'+this.patient.key+'/medicines').push(this.medicine);


  } 
=======
  // submitMedsForm(db: AngularFireDatabase,auth:AngularFireAuth):any{
  //   this.medicine.brandName=this.brandName;
  //   this.medicine.dateStarted=this.dateTime;
  //   for(var  i=0;i<4;i++){
  //     if(this.meds[i]!=null){
  //       this.medicine.consumptionTimings.push(this.meds[i]);
  //     }
  //   }
  //   this.medicine.prescriptionBy="Dr."+firebase.auth().currentUser.displayName;
  submitMedsForm():any{
    console.log(this.medicine);
    this.display="none";
    this.medicine.brandName=this.brandName;
    this.medicine.dateStarted=this.dateTime;
    for(var  i=0;i<4;i++){
      if(this.meds[i]!=null){
        console.log(this.meds[i]);
        console.log(this.medicine.consumptionTimings);
        this.medicine.consumptionTimings.push(this.meds[i]);
      }
    }
    this.medicine.prescriptionBy="Dr."+firebase.auth().currentUser.displayName;

  // var ref = db.list('/wards/'+firebase.auth().currentUser.uid+'/'+this.patient.key+'/medicines').push(this.medicine);
  // } 
>>>>>>> more part done
  var ref = this.db.list('/wards/'+firebase.auth().currentUser.uid+'/'+this.patient.key+'/medicines').push(this.medicine);

  } 
  onSelect(hero: Patient): void {
    console.log(hero.medicines);
  }

  // onSelect(hero: Patient): void {
  //   this.selectedPatient = hero;
  //   console.log(this.selectedPatient);
  // }

  // display="none";
  //   let isDone: boolean = false; }

  ngOnInit() {
    console.log(this.patient);  
    console.log(this.patient.medicines);
  }

}
