import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';
import { NewDoctor } from '../newDoctor';


@Component({
  selector: 'app-new-doctor-register',
  templateUrl: './new-doctor-register.component.html',
  styleUrls: ['./new-doctor-register.component.scss']
})
export class NewDoctorRegisterComponent implements OnInit {
  constructor(db: AngularFireDatabase, private router: Router) {
    this.currentUser = firebase.auth().currentUser;
    console.log(this.currentUser.displayName);
    db.list('/Users/Doctors').valueChanges();
  }

  currentUser;
  phone;
  age;
  newDoctor:NewDoctor=new NewDoctor();


  registerClicked(){
    console.log(this.phone+" "this.degree+" "+this.age);
    this.newDoctor.phone = this.phone;
    this.newDoctor.age = this.age;
    this.newDoctor.email = this.currentUser.email;
    this.newDoctor.name = this.currentUser.displayName;
    this.newDoctor.uid = this.currentUser.uid;
    firebase.database().ref('/Users/Doctors').push(this.newDoctor);
    this.router.navigate(['/doctor']);
    //var doctors = firebase.database().ref('/Users/Doctors');
    //console.log(doctors);
  }

  ngOnInit() {
    
  }

}
