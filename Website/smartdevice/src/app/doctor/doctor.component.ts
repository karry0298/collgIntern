import { Component, OnInit, Input  } from '@angular/core';
import { NgModule }      from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AngularFireDatabase } from 'angularfire2/database';
import { Patient } from '../patient';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {
  products: any[];
  name: any;
  selectedPatient: Patient;
  filtername: string="";

onSelect(hero: Patient): void {
  this.selectedPatient = hero;
  console.log(this.selectedPatient);
}
// console.log(filter);

filter(patient : Patient) : boolean{
  // Return true if don't want this job in the results.
  // e.g. lets filter jobs with price < 25;
  if (patient.name.toUpperCase().indexOf(this.filtername.toUpperCase())>-1){
    return false;
  }
  return true; 
}


  constructor(db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router) {
    db.list('/wards')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      console.log(this.products);
    });
  }

logout() {
  this.afAuth.auth.signOut();
  console.log('logged out');
  this.router.navigateByUrl('');
}

  ngOnInit(){}

}
