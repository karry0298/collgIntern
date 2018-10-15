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
  myParams: object = {};
  constructor(db: AngularFireDatabase,public afAuth: AngularFireAuth, private router: Router) {
    this.user = afAuth.authState;
    db.list('/Users/Docters').valueChanges().subscribe((val => {this.doctors = val;}))
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
        if(doctor.key == uid) {
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
  this.myParams = {
  particles: {
    number: { value: 160, density: { enable: true, value_area: 800 } },
    color: { value: "#ffffff" },
    shape: {
      type: "circle",
      stroke: { width: 0, color: "#000000" },
      polygon: { nb_sides: 5 },
      image: { src: "img/github.svg", width: 100, height: 100 }
    },
    opacity: {
      value: 1,
      random: true,
      anim: { enable: true, speed: 1, opacity_min: 0, sync: false }
    },
    size: {
      value: 3,
      random: true,
      anim: { enable: false, speed: 4, size_min: 0.3, sync: false }
    },
    line_linked: {
      enable: false,
      distance: 150,
      color: "#ffffff",
      opacity: 0.4,
      width: 1
    },
    move: {
      enable: true,
      speed: 1,
      direction: "none",
      random: true,
      straight: false,
      out_mode: "out",
      bounce: false,
      attract: { enable: false, rotateX: 600, rotateY: 600 }
    }
  },
  interactivity: {
    detect_on: "canvas",
    events: {
      onhover: { enable: true, mode: "bubble" },
      onclick: { enable: true, mode: "repulse" },
      resize: true
    },
    modes: {
      grab: { distance: 155.84415584415586, line_linked: { opacity: 1 } },
      bubble: {
        distance: 250,
        size: 3.996003996003996,
        duration: 7.112887112887113,
        opacity: 0.5834165834165834,
        speed: 3
      },
      repulse: { distance: 127.87212787212788, duration: 0.4 },
      push: { particles_nb: 4 },
      remove: { particles_nb: 2 }
    }
  },
  retina_detect: true
};
  }

  printDoctors(){
    console.log(this.doctors);
  }

}
