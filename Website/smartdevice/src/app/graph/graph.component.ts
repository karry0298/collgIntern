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
  objectKeys = Object.keys;
  age = [];
  name =[];
  chart = []; //to hold chart info

  constructor(db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router) {
    db.list('/wards')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      console.log(this.products);
      for(let manager of this.products){
        for(let key of this.objectKeys(manager)){
          this.age.push(manager[key].age);
          this.name.push(manager[key].name);
          //console.log(manager[key].age);
        }
      }
      this.printData();
      // this.age = product.map(product => product.age);
      // this.name = product.map(product => product.name);
    });
  }



  ngOnInit() {

  }

  printData(){
    //this.age.splice(29,2);
    //this.name.splice(29,2);
    //console.log(this.age);
    //console.log(this.name);
    this.chart = new Chart('canvas', {
      type: 'line',
      data: {
        labels: this.name,
        datasets: [
          {
            data: this.age,
            borderColor: "#186B95",
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: false,
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
