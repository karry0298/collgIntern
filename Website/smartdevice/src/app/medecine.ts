import { Time } from "@angular/common";

export class Medecine {
    brandName:String;
    consumptionTimings:String[];
    count:number;
    dateStopped:String;
    dateStarted:String;
    name:String;
    prescriptionBy:String;
    dailyEventID:number;
    dueEventID:number;



    constructor(){
        this.brandName="";
        this.name="";
        this.prescriptionBy="";
        this.consumptionTimings=[];
        this.dateStarted=null;
        this.dateStopped=null;
        this.count=5;
        this.dailyEventID = -1;
        this.dueEventID = 0;
    }
}
