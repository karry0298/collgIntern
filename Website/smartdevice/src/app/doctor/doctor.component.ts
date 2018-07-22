import { Component, OnInit, Input  } from '@angular/core';
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

onSelect(hero: Patient): void {
  this.selectedPatient = hero;
  console.log(this.selectedPatient);
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
