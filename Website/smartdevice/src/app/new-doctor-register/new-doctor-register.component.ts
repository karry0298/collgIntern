import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireDatabase } from 'angularfire2/database';


@Component({
  selector: 'app-new-doctor-register',
  templateUrl: './new-doctor-register.component.html',
  styleUrls: ['./new-doctor-register.component.scss']
})
export class NewDoctorRegisterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    
  }

}
