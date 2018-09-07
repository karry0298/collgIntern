import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  error: any;
  user: Observable<firebase.User>;
  doctors:any[];
  constructor(db: AngularFireDatabase,public afAuth: AngularFireAuth, private router: Router) {
    this.user = afAuth.authState;
    db.list('/Users/Doctors').valueChanges().subscribe((val => {this.doctors = val;}))
    // this.doctors = db.list('/doctors').push({name: "Ryan",uid: "7wfLyZWqBVVfWeFZbiDA99c04ka2", email:"Ryan@gmail.com", specialiation:"MD", phone:"9482374322" });
  }

  login() {
    this.afAuth.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider()
  ).then(
    (success) => {
      var uid = firebase.auth().currentUser.uid
      var flag = false;
      //console.log(uid);
      for(let doctor of this.doctors) {
        if(doctor.uid == uid) {
          flag = true;
          this.router.navigate(['/doctor']);
          console.log('already reg');
          break;
        }
      }
      if (flag == false)  {
        console.log("New User");
        this.router.navigate(['/register']);
      }

    }).catch(
      (err) => {
        this.error = err;
      })

      //console.log(firebase.auth().currentUser);
  }

  ngOnInit() {
  //   particlesJS.load('particles-js', 'assets/particles.json', function() {
  //   console.log('callback - particles.js config loaded');
  // });
  }

  printDoctors(){
    console.log(this.doctors);
  }

}
