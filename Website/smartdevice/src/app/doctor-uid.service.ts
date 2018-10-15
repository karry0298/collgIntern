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


  
  products:any[];
  currentPatient:any[]
  currentUserId():any{
    return firebase.auth().currentUser.uid;
  }
  currentUser():any{
    console.log(firebase.auth().currentUser.uid);
    return firebase.auth().currentUser.displayName;
  }
  init():Observable<any[]>{
    // console.log(this.db.list('/LinksDoctorsPatients')
    // .valueChanges());
   return  this.db.list('/LinksDoctersPatients')
    .valueChanges();
    // .subscribe((product:any[]) => {
    //   this.products = product;
    //   console.log(this.products);
    // });
    // console.log(this.products);
  }
  initPatients():Observable<any[]>{
   return  this.db.list('/Users/Patients')
    .valueChanges();
    // .subscribe((product1:any[]) => {
    //   this.currentPatient = product1;
    //   console.log(this.currentPatient);


    // });
    // console.log(this.currentPatient);    
  }


    patientList():any[]{
      console.log(this.currentPatient);
      return this.currentPatient;
    }


    patientDoctorList():any[]{
      console.log(this.products);
      return this.products;
    }



}
