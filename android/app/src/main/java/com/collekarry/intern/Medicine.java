package com.collekarry.intern;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Medicine implements Serializable{

    private String name;
    private String brandName;
    private Date dateStarted;
    private Date dateStopped;
    private int count;
    private long dueEventID;
    private String reasonStopped;
    private List<String> consumptionTimings;
    private String prescriptionBy;

    public String getNextConsumptionTime(LocalTime t){
        if(consumptionTimings == null || consumptionTimings.size() == 0){
            return null;
        }
        LocalTime x = new LocalTime(23,59);
        LocalTime tomoTime = new LocalTime(23,59);

        for(int i = 0; i < consumptionTimings.size(); i++){

            LocalTime a = LocalTime.parse(consumptionTimings.get(i), DateTimeFormat.forPattern("hh:mm a").withLocale(Locale.ENGLISH));

            if( a.isBefore(x) && (a.isAfter(t) || a.isEqual(t))  ){
                x = a;
            }

            if(a.isBefore(tomoTime)){
                tomoTime = a;
            }
        }

        if(x.isEqual(new LocalTime(23,59))){
//            System.out.println("\t"+ name+"\t"+x.toString("hh:mm a"));
            return "Tomorrow, " + tomoTime.toString("hh:mm a");
        }
        else{
            return "Today, " + x.toString("hh:mm a");
        }
    }

    public DateTime getNextConsumptionDateTime(LocalTime t){
        if(consumptionTimings == null || consumptionTimings.size() == 0){
            return new DateTime();
        }
        LocalTime x = new LocalTime(23,59);
        LocalTime tomoTime = new LocalTime(23,59);

        for(int i = 0; i < consumptionTimings.size(); i++){
            LocalTime a = LocalTime.parse(consumptionTimings.get(i), DateTimeFormat.forPattern("hh:mm a").withLocale(Locale.ENGLISH));
            if( a.isBefore(x) && (a.isAfter(t) || a.isEqual(t))  ){
                x = a;
            }

            if(a.isBefore(tomoTime)){
                tomoTime = a;
            }
        }

        if(x.isEqual(new LocalTime(23,59))){
//            System.out.println("\t"+ name+"\t"+x.toString("hh:mm a"));
            return tomoTime.toDateTimeToday().plusDays(1);
        }
        else{
            return x.toDateTimeToday();
        }
    }

    public DateTime getDueDate(){
        DateTime d = new DateTime(dateStarted).withTime(23,59,59,999);
        int pillsPerDay = consumptionTimings.size();
        int days = Integer.valueOf(count/pillsPerDay) - 3;

        return d.plusDays(days);
    }

    public Medicine() {
    }

    public Medicine(String name, String brandName, int count, Date dateStarted, Date dateStopped, List<String> consumptionTimings, String prescriptionBy) {
        this.name = name;
        this.brandName = brandName;
        this.count = count;
        this.dateStarted = dateStarted;
        this.dateStopped = dateStopped;
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

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
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

    public long getDueEventID() {
        return dueEventID;
    }
    public void setDueEventID(long dueEventID) {
        this.dueEventID = dueEventID;
    }
}
