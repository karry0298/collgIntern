import { Time } from "@angular/common";

export class Medecine {
    brandName:String;
    consumptionTimings:Time[];
    count:number;
    dateEnded:Date;
    dateStarted:Date;
    Medname:String;
    presCribedBy:String;
    constructor(){
        this.brandName="";
        this.Medname="";
        this.presCribedBy="";
        this.consumptionTimings=[];
        this.dateStarted=null;
    }
}
