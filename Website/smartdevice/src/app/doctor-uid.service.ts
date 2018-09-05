import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';

@Injectable({
  providedIn: 'root'
})
export class DoctorUIDService {
  products: any[];
  
  currentUser():any{
    console.log(firebase.auth().currentUser.uid);
    return firebase.auth().currentUser.displayName;
  }
  patientList():any[]{
    return this.products;
  }



    constructor(private db: AngularFireDatabase, auth:AngularFireAuth) { 
      db.list('/LinksDoctorsPatients')
      .valueChanges()
      .subscribe(product => {
        this.products = product;
        console.log(this.products);


      });
    }



}
