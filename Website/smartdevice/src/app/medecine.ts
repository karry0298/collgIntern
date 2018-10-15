import { Time } from "@angular/common";

export class Medecine {
    brandName:String;
    consumptionTimings:String[];
    count:number;
    dateEnded:Date;
    dateStarted:Date;
    name:String;
    prescriptionBy:String;
    constructor(){
        this.brandName="";
        this.name="";
        this.prescriptionBy="";
        this.consumptionTimings=[];
        this.dateStarted=null;
        this.dateEnded=null;
        this.count=5;
    }
}
