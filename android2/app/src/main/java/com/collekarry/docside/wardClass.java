package com.collekarry.docside;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.List;

public class wardClass implements Serializable
{
    private String key;
    private String name;
    private int age;
    private String imp;
    private String gender;
    private String uid;
    private List<Medicine> medicines;

    public wardClass() {

    }

    public wardClass(String key, String name, int age, String gender, String uid,String imp) {
        this.key = key;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.imp = imp;
        this.uid = uid;
    }

    public boolean addMedicine(Medicine e){
        final boolean[] ret = new boolean[1];

        medicines.add(e);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("wards").child(uid).child(key).setValue(this)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ret[0] = true;
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ret[0] = false;
            }
        });
        return ret[0];
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

    public String getImp() {
        return imp;
    }

    public void setImp() {
        this.imp = imp;
    }


}
