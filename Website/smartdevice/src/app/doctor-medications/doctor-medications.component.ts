import { Component, OnInit ,Input,OnChanges} from '@angular/core';
import { Patient } from '../patient';
import { Medecine } from '../medecine';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import { SimpleChanges } from '@angular/core';
@Component({
  selector: 'app-doctor-medications',
  templateUrl: './doctor-medications.component.html',
  styleUrls: ['./doctor-medications.component.scss']
})
export class DoctorMedicationsComponent implements OnInit,OnChanges {
  constructor(private db: AngularFireDatabase) {
    db.list('/wards').valueChanges();
  }
  @Input() patient: Patient;
  medicine:Medecine=new Medecine();
  medsList:any[];
  medslength:number;
  brandName:String="";
  name:String="";
  objectKeys = Object.keys;
  auth:AngularFireAuth;

  dateTime:Date;
  // str1:String="";str2:String="";str3:String="";str4:String="";
  meds=[];


display:String="none";

  openModal(): void{
    this.display="block";
    console.log(this.display);
  }
  closeModal(): void{
    this.display="none";
  }

  submitMedsForm():any{
    console.log(this.medicine);
    this.display="none";
    this.medicine.brandName=this.brandName;
    this.medicine.dateStarted=this.dateTime;
    this.medicine.name=this.name;
    for(var  i=0;i<4;i++){
      if(this.meds[i]!=null){
        console.log(this.meds[i]);
        console.log(this.medicine.consumptionTimings);
        this.medicine.consumptionTimings.push(this.meds[i]);
        this.meds[i]="";
      }
    }

    this.medicine.prescriptionBy="Dr."+firebase.auth().currentUser.displayName;
    // var newPostKey = firebase.database().ref().child('/wards/'+firebase.auth().currentUser.uid+'/'+this.patient.key+'/medicines').push().key;
    // var updates = {};
    // updates[ '/wards/'+firebase.auth().currentUser.uid+'/'+this.patient.key + newPostKey] = this.medicine;
    // firebase.database().ref().update(updates);

    console.log(firebase.auth().currentUser.uid+" "+this.patient.key);
  this.db.list('Users/Patients/'+this.patient.key+'/medicines').push(this.medicine);




  }

  // submitMedsForm(db: AngularFireDatabase,auth:AngularFireAuth):any{
  //   this.medicine.brandName=this.brandName;
  //   this.medicine.dateStarted=this.dateTime;
  //   for(var  i=0;i<4;i++){
  //     if(this.meds[i]!=null){
  //       this.medicine.consumptionTimings.push(this.meds[i]);
  //     }
  //   }
  //   this.medicine.prescriptionBy="Dr."+firebase.auth().currentUser.displayName;

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
    this.db.list('Users/Patients/'+this.patient.key+'/medicines').valueChanges().subscribe((meds:any[]) => {

      this.medsList=meds;
      this.medslength=this.medsList.length;
      console.log(this.medslength);

  });
  }
  ngOnChanges(changes: SimpleChanges) {

    console.log(changes.patient.previousValue);
    if(changes.patient.previousValue){
      console.log(this.patient.medicines);
      this.db.list('Users/Patients/'+this.patient.key+'/medicines').valueChanges().subscribe((meds:any[]) => {
  
        this.medsList=meds;
        this.medslength=this.medsList.length;
        console.log(this.medslength);
  
  
    });
    }
    // You can also use categoryId.previousValue and 
    // categoryId.firstChange for comparing old and new values

}

}
