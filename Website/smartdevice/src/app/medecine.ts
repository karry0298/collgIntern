import { Time } from "@angular/common";

export class Medecine {
    brandName:String;
    consumptionTime:String[];
    count:number;
    dateEnded:Date;
    dateStarted:Date;
    medName:String;
    presCribedBy:String;
    constructor(){
        this.brandName="";
        this.medName="";
        this.presCribedBy="";
        this.consumptionTime=[];
        this.dateStarted=null;
    }
}
