export class Medecine {
    brandName:String;
    name:String;
    prescriptionBy:String;
    consumptionTimings:String[];
    dateStarted:Date;
    constructor(){
        this.brandName="";
        this.name="";
        this.prescriptionBy="";
        this.consumptionTimings=[""];
        this.dateStarted=null;
    }
}
