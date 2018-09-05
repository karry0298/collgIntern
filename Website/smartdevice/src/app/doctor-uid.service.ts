import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


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

  private messageSource = new BehaviorSubject('default message');
    currentMessage = this.messageSource.asObservable();

    constructor() { }



    patientList():any[]{
      console.log(this.products);
      return this.products;
    }

}
