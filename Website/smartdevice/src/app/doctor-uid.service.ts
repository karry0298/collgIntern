import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
@Injectable({
  providedIn: 'root'
})
export class DoctorUIDService {
  products: any[];



    constructor(private db: AngularFireDatabase) { 
      db.list('/LinksDoctorsPatients')
      .valueChanges()
      .subscribe(product => {
        this.products = product;
        console.log(this.products);
        console.log(this);

      });
    }



}
