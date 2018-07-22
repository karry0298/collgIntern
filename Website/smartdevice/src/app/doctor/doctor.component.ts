import { Component, OnInit, Input  } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database';
import { Patient } from '../patient';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {
  products: any[];

  selectedPatient: Patient;

onSelect(hero: Patient): void {
  this.selectedPatient = hero;
  console.log(this.selectedPatient);
}




  constructor(db: AngularFireDatabase) {
    db.list('/wards')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      console.log(this.products);
    });
  }

  ngOnInit(){}

}
