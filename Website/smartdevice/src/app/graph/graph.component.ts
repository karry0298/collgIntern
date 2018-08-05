import { Component, OnInit, Input } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Chart } from 'chart.js';
import { AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.scss']
})
export class GraphComponent implements OnInit {
  products: any[];
  age: any[];
  name: any[];
  chart = []; //to hold chart info

  constructor(db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router) {
    db.list('/wards')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      console.log(this.products);
      this.age = product.map(product => product.age);
      this.name = product.map(product => product.name);
    });
  }

  printData(){
    this.age.splice(29,2);
    this.name.splice(29,2);
    console.log(this.age);
    console.log(this.name);
  }

  ngOnInit() {
    let age = this.age;
    let name = this.name;
    this.chart = new Chart('canvas', {
            type: 'line',
            data: {
              labels: name,
              datasets: [
                {
                  data: age,
                  borderColor: "#3cba9f",
                  fill: false
                },
              ]
            },
            options: {
              legend: {
                display: true,
						  }
            },
              scales: {
                xAxes: [{
                  display: true,
                }],
                yAxes: [{
                  display: true
                }],
              }
          });
  }

}
