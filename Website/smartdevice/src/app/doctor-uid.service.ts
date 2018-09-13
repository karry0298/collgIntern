import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';

@Injectable({
  providedIn: 'root'
})
export class DoctorUIDService {
  constructor(private db: AngularFireDatabase, auth:AngularFireAuth) { 


  }



  products:Observable< any[]>;
  currentPatient:Observable<any[]>;
  currentUser():any{
    console.log(firebase.auth().currentUser.uid);
    return firebase.auth().currentUser.displayName;
  }
  init():void{
    this.db.list('/LinksDoctorsPatients')
    .valueChanges()
    .subscribe(product => {
      this.products. = product;
      console.log(this.products);


    });
    console.log(this.products);
  }
  initPatients():void{
    this.db.list('/Users/Patients')
    .valueChanges()
    .subscribe(product => {
      this.currentPatient. = product;
      console.log(this.currentPatient);


    });
    console.log(this.currentPatient);    
  }





    patientList():any[]{
      console.log(this.products);
      return this.products;
    }



}
