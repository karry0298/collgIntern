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
  chartHeartRate = []; //to hold chart info
  chartSugar=[];
  chartBP=[]
  chartCholestrol=[]
  constructor(db: AngularFireDatabase, public afAuth: AngularFireAuth, private router: Router) {
    db.list('/Users/Patients')
    .valueChanges()
    .subscribe(product =>{
      this.products = product;
      
      console.log(this.products);
      for(let manager of this.products){
       
          this.age.push(manager.age);
          //this.name.push(manager[key].name);
          // console.log(manager.age);
        
      }
      console.log(this.age);
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
    this.chartHeartRate = new Chart('canvas1', {
      type: 'line',
      data: {
        labels: ["Mon", "Tues", "Wed", "Thrus", "Fri", "Sat", "Sun"],
        datasets: [
          {
            data: this.age,
            label: "HeartRate",
            lineTension: 0.3,
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(78, 115, 223, 1)",
            pointBorderColor: "rgba(78, 115, 223, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: true,
        },
      },
        scales: {
          xAxes: [{
            display: true,
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            display: true,
          }],
        }
    });


    this.chartSugar = new Chart('canvas2', {
      type: 'line',
      data: {
        labels: ["Mon", "Tues", "Wed", "Thrus", "Fri", "Sat", "Sun"],
        datasets: [
          {
            data: this.age,
            label: "Glucose Levels",
            lineTension: 0.3,
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(78, 115, 223, 1)",
            pointBorderColor: "rgba(78, 115, 223, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: true,
        },
      },
        scales: {
          xAxes: [{
            display: true,
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            display: true,
          }],
        }
    });



    this.chartBP = new Chart('canvas3', {
      type: 'line',
      data: {
        labels: ["Mon", "Tues", "Wed", "Thrus", "Fri", "Sat", "Sun"],
        datasets: [
          {
            data: this.age,
            label: "Blood Pressure",
            lineTension: 0.3,
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(78, 115, 223, 1)",
            pointBorderColor: "rgba(78, 115, 223, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: true,
        },
      },
        scales: {
          xAxes: [{
            display: true,
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            display: true,
          }],
        }
    });

    this.chartCholestrol = new Chart('canvas4', {
      type: 'line',
      data: {
        labels: ["Mon", "Tues", "Wed", "Thrus", "Fri", "Sat", "Sun"],
        datasets: [
          {
            data: this.age,
            label: "Cholestrol",
            lineTension: 0.3,
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(78, 115, 223, 1)",
            pointBorderColor: "rgba(78, 115, 223, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
            fill: true
          },
        ]
      },
      options: {
        legend: {
          display: true,
        },
      },
        scales: {
          xAxes: [{
            display: true,
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false,
              drawBorder: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            display: true,
          }],
        }
    });


  }

}
