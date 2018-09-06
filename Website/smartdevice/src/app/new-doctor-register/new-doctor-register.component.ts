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
  constructor(db: AngularFireDatabase) {
    this.currentUser = firebase.auth().currentUser;
    console.log(this.currentUser.displayName);
    db.list('/Users/Doctors').valueChanges();
  }

  currentUser;
  phone;
  degree;
  age;
  newDocter:NewDoctor=new NewDoctor();


  registerClicked(){
    console.log(this.phone+" "this.degree+" "+this.age);
    this.newDocter.phone = this.phone;
    this.newDocter.degree = this.degree;
    this.newDocter.age = this.age;
    this.newDocter.email = this.currentUser.email;
    this.newDocter.name = this.currentUser.displayName;
    this.newDocter.uid = this.currentUser.uid;
    //var ref = this.db.list('/Users/Doctors').push(this.newDocter);

  }

  ngOnInit() {
  }

}
