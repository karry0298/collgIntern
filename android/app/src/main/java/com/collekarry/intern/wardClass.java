package com.collekarry.intern;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.LocalTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wardClass implements Serializable
{
    private String key;
    private String name;
    private int age;
    private String imp;
    private String gender;
    private String uid;
    private List<Medicine> medicines;
    private List<History> histories;
    private long docID;


    public long getDocID() {
        return docID;
    }

    public void setDocID(long docID) {
        this.docID = docID;
    }

    public wardClass() { }

    public wardClass(String key, String name, int age, String gender, String uid , String imp) {
        this.key = key;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.uid = uid;
        this.imp = imp;
        this.medicines = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    public void addMedicine(Medicine e){
        final boolean[] ret = new boolean[1];

        if(medicines == null){
            medicines = new ArrayList<>();
        }

        medicines.add(e);

    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public void addHistory(History history)
    {
        if(histories == null)
        {
            histories = new ArrayList<>();
        }
        this.histories.add(history);
    }

    public String getImp() {
        return imp;
    }

    public void setImp() {
        this.imp = imp;
    }
}
