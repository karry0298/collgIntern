import { Time } from "@angular/common";

export class Medecine {
    brandName:String;
    name:String;
    prescriptionBy:String;
    consumptionTimings:Time[];
    dateStarted:Date;
    constructor(){
        this.brandName="";
        this.name="";
        this.prescriptionBy="";
        this.consumptionTimings=[];
        this.dateStarted=null;
    }
}
