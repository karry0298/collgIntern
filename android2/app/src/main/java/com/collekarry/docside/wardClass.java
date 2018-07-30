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
    private String gender;
    private String uid;
    private List<Medicine> medicines;

    public wardClass() {

    }

    public wardClass(String key, String name, int age, String gender, String uid) {
        this.key = key;
        this.name = name;
        this.age = age;
        this.gender = gender;
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
//    public int getImageId() {
//        return imageId[0];
//    }
//    public int getImageId(int index) {
//
//        int i=0;
//
//        while(i<10 && this.imageId[i] != 0){
//            i++;
//        }
//        if(index < i) {
//            return imageId[index];
//        }
//        else{
//            return 0;
//        }
//    }

//    public void setImageId(int imageId, int index) {
//        this.imageId[index] = imageId;
//    }

//    public boolean setImageId(int imageId) {
//        int index=0;
//
//        while(index<10 && this.imageId[index] != 0){
//            index++;
//        }
//        if(index==10){
//                return false;
//        }
//
//        this.imageId[index] = imageId;
//        return true;
//
//    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

//    public Map<String,Object> toMap(){
//        Map<String, Object> A = new HashMap<>();
//        Map<String, Object> meds = new HashMap<>();
//
//            for(Medicine m: medicines){
//                Map<String, Object> medDetails = new HashMap<>();
//                    medDetails.put("brandName", m.getBrandName());
//                    medDetails.put("dateStarted", m.getDateStopped());
//                    medDetails.put("reasonStopped", m.getReasonStopped());
//                    Map<String, Object> t = new HashMap<>();
//                        for(LocalTime p: m.getConsumptionTimings()){
//                            t.put("0", p);
//                        }
//                    medDetails.put("consumptionTimings",t);
//                    medDetails.put("prescriptionBy", m.getPrescriptionBy());
//
//                meds.put(m.getName(), medDetails);
//            }
//
//        A.put("name", this.name);
//        A.put("age", this.age);
//        A.put("gender", this.gender);
//        A.put("uid", this.uid);
//        A.put("Medicines", meds);
//
//        return A;
//    }
}
