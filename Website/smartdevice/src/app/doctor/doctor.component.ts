import { Component, OnInit, Input } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AngularFireDatabase } from 'angularfire2/database';
import { Patient } from '../patient';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
// import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { switchMap } from 'rxjs/operators';
import { Subject } from 'rxjs/Subject';
import { Observable, Subscription, BehaviorSubject } from 'rxjs';

import { DoctorUIDService } from "../doctor-uid.service";
import { AngularFireStorage } from 'angularfire2/storage';


@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {
  doctorpatients: any[];
  patients:any[];
  finalPatientList:any[]=[];
  patientDisplayData:any[];
  objectKeys = Object.keys;
  name: any;
  selectedPatient: Patient;
  filtername: string = "";
  show: boolean = true;
  user: any;
  displayPatientData:Patient[]=[];
  message:string;

  
  showPatientData():void{
    this.finalPatientList=[];
    for(var i=0;i<this.doctorpatients.length;i++){

      if(Object.values(this.doctorpatients[i])[0]==this.data.currentUserId())
        {
        // console.log(Object.keys(this.doctorpatients[i])[0]);
         this.finalPatientList.push(Object.keys(this.doctorpatients[i])[0]);

        //  console.log(this.finalPatientList);
        }
       

    }

    // for(var i=0;this.finalPatientList.length;i++){
      console.log(this.finalPatientList);
    // }
  }
  mapPatientData():void{
   var patientKeys:any=Object.keys(this.patients);
   for(var i=0;i<this.patients.length;i++){
    for(var j=0;j<this.finalPatientList.length;j++){
      console.log(this.patients[i].key+"  "+this.finalPatientList[j]);
      if(this.patients[i].key==this.finalPatientList[j]){
          this.displayPatientData.push(this.patients[i]);
      }
    }
   }
   console.log(this.displayPatientData);
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
  // console.log(filter);
  getUid(): string {
    //console.log(firebase.auth().currentUser.uid);
    //console.log(firebase.auth().currentUser.displayName);
    return firebase.auth().currentUser.displayName;
  }
  getName():string{
    return  firebase.auth().currentUser.displayName;
  }


  filter(patient: Patient): boolean {
    // Return true if don't want this job in the results.
    // e.g. lets filter jobs with price < 25;

    if (patient.name.toLowerCase().indexOf(this.filtername.toLowerCase()) > -1) {
      return true;
    }
    return false;
  }

  getDoctorPatients():void{
    var patientsList:any[];
    this.data.init().subscribe((product1:any[]) => {
        patientsList = product1;
        this.doctorpatients=patientsList;

        console.log(this.doctorpatients);
        this.showPatientData();
    });
      console.log(this.doctorpatients);
  }

  // ,{
  //   query: {
  //     orderByChild: 'uid',
  //     equalTo: 'fish'
  //   }
  // }
  getPatients():void{
    var patientsList:any[];
    this.data.initPatients().subscribe((product1:any[]) => {
      patientsList = product1;
      this.patients=patientsList;
      this.mapPatientData();
      console.log(this.patients);

  });

    
  }

  constructor(private afStorage: AngularFireStorage, db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router, private data: DoctorUIDService) {
    // data.init();


    this.user = this.getUid();

    // console.log(db.list('/wards').valueChanges());

    this.filtername = "";
    // this.products=data.patientDoctorList();
    console.log(data.patientDoctorList());

    // db.list('/wards')
    //   .valueChanges()
    //   .subscribe(product => {
    //     this.products = product;
    //     console.log(this.products);
    //     console.log(this);

    //   });

    //     const size$ = new Subject<string>();
    //   const queryObservable = size$.pipe(
    //   switchMap(size =>
    //     db.list('/wards', ref => ref.orderByChild('size').equalTo(size)).valueChanges()
    //   )
    // );
  }

  logout() {
    this.afAuth.auth.signOut();
    console.log('logged out');
    this.router.navigateByUrl('');
  }

  ngOnInit() {
    this.getDoctorPatients();
    this.getPatients();
    // this.data.currentMessage.subscribe(message => this.message = message

}



}
