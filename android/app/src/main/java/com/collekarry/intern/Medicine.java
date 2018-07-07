package com.collekarry.intern;

import java.sql.Time;
import java.util.Date;

public class Medicine {

    private String name;
    private String brandName;
    private Date dateStarted;
    private Date dateStopped;
    private String reasonStopped;
    private Time[] consumptionTimings;
    private String prescriptionBy;

    public Medicine(String name, String brandName, Date dateStarted, Time[] consumptionTimings, String prescriptionBy) {
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

    public Time[] getConsumptionTimings() {
        return consumptionTimings;
    }

    public void setConsumptionTimings(Time[] consumptionTimings) {
        this.consumptionTimings = consumptionTimings;
    }

    public String getPrescriptionBy() {
        return prescriptionBy;
    }

    public void setPrescriptionBy(String prescriptionBy) {
        this.prescriptionBy = prescriptionBy;
    }
}
