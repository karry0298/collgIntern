import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';

@Injectable({
  providedIn: 'root'
})
export class DoctorUIDService {
  constructor(private db: AngularFireDatabase, auth:AngularFireAuth) { 


  }
  products: any[];
  
  currentUser():any{
    console.log(firebase.auth().currentUser.uid);
    return firebase.auth().currentUser.displayName;
  }
  init(){
    this.db.list('/LinksDoctorsPatients')
    .valueChanges()
    .subscribe(product => {
      this.products = product;
      console.log(this.products);


    });
  }





    patientList():any[]{
      console.log(this.products);
      return this.products;
    }



}
