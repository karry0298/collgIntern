package com.collekarry.intern;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

import org.joda.time.LocalTime;
import java.util.Date;
import java.util.List;


public class Medicine implements Serializable{

    private String name;
    private String brandName;
    private Date dateStarted;
    private Date dateStopped;
    private String reasonStopped;
    private List<String> consumptionTimings;
    private String prescriptionBy;

    public String getNextConsumptionTime(LocalTime t){
        if(consumptionTimings == null || consumptionTimings.size() == 0){
            return null;
        }
        LocalTime x = new LocalTime(23,59);

        for(int i = 0; i < consumptionTimings.size(); i++){
            LocalTime a = LocalTime.parse(consumptionTimings.get(i));

            if( a.isBefore(x) && (a.isAfter(t) || a.isEqual(t))  ){
                x = a;
            }
        }

        if(x.isEqual(new LocalTime(23,59))){
            return null;
        }
        else{
            return x.toString("HH:mm:ss");
        }
    }

    public Medicine() {
    }

    public Medicine(String name, String brandName, Date dateStarted, List<String> consumptionTimings, String prescriptionBy) {
        this.name = name;
        this.brandName = brandName;
        this.dateStarted = dateStarted;
        this.consumptionTimings = consumptionTimings;
        this.prescriptionBy = prescriptionBy;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getDateStarted() {
        return dateStarted;
    }
    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateStopped() {
        return dateStopped;
    }
    public void setDateStopped(Date dateStopped) {
        this.dateStopped = dateStopped;
    }

    public String getReasonStopped() {
        return reasonStopped;
    }
    public void setReasonStopped(String reasonStopped) {
        this.reasonStopped = reasonStopped;
    }

    public List<String> getConsumptionTimings() {
        return consumptionTimings;
    }
    public void setConsumptionTimings(List<String> consumptionTimings) {
        this.consumptionTimings = consumptionTimings;
    }

    public String getPrescriptionBy() {
        return prescriptionBy;
    }
    public void setPrescriptionBy(String prescriptionBy) {
        this.prescriptionBy = prescriptionBy;
    }
}
