import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.scss']
})
export class DoctorComponent implements OnInit {
  products: any[];
  constructor(db: AngularFireDatabase) {
    db.list('/abc')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      console.log(this.products);
    });
  }

  ngOnInit()
   // tslint:disable-next-line:one-line
   {
  this.db.list('/abc')
  .valueChanges()
  .subscribe(product => {
    this.products = product;
       console.log(this.products);
  } );

   }

}
