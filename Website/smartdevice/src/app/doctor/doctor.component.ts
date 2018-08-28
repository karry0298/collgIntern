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

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {
  products: any[];
  objectKeys = Object.keys;
  name: any;
  selectedPatient: Patient;
  filtername: string = "";
  show: boolean = true;
  user: any;
  onSelect(hero: Patient): void {
    this.selectedPatient = hero;
    console.log(this.selectedPatient);
  }
  // console.log(filter);
  getUid(): string {
    console.log(firebase.auth().currentUser.displayName);
    return firebase.auth().currentUser.uid;
  }


  filter(patient: Patient): boolean {
    // Return true if don't want this job in the results.
    // e.g. lets filter jobs with price < 25;

    if (patient.name.indexOf(this.filtername) > -1) {
      return true;
    }
    return false;
  }

  // ,{
  //   query: {
  //     orderByChild: 'uid',
  //     equalTo: 'fish'
  //   }
  // }


  constructor(db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router) {
    this.user = this.getUid();
    // console.log(db.list('/wards').valueChanges());

this.filtername = "";



    db.list('/wards')
      .valueChanges()
      .subscribe(product => {
        this.products = product;
        console.log(this.products);
        console.log(this);

      });

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

  ngOnInit() { }

}
