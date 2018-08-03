package com.collekarry.docside;

public class MyBlaBlaVnew
{
   String daProb;
   String discription;
   String date;
   String time;

    public MyBlaBlaVnew(){}

    public MyBlaBlaVnew(String daProb, String discription,String date , String time) {
        this.daProb = daProb;
        this.discription = discription;
        this.date = date;
        this.time = time;
    }

    public String getDaProb() {
        return daProb;
    }

    public String getDiscription() {
        return discription;
    }

    public String getDate() { return date; }

    public String getTime() { return time; }
}
