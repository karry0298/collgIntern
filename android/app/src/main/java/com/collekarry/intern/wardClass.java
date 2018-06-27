package com.collekarry.intern;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class wardClass
{
    private String name;
    private int age;
    private String gender;
    private Integer[] imageId;

    public wardClass(String name, int age, String gender, int imageId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.imageId = new Integer[10];
        this.imageId[0] = imageId;
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

    public int getImageId() {
        return imageId[0];
    }

    public int getImageId(int index) {

        int i=0;

        while(i<10 && this.imageId[i] != 0){
            i++;
        }
        if(index < i) {
            return imageId[index];
        }
        else{
            return 0;
        }
    }

//    public void setImageId(int imageId, int index) {
//        this.imageId[index] = imageId;
//    }

    public boolean setImageId(int imageId) {
        int index=0;

        while(index<10 && this.imageId[index] != 0){
            index++;
        }
        if(index==10){
                return false;
        }

        this.imageId[index] = imageId;
        return true;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String,Object> toMap(){
        Map<String, Object> A = new HashMap<>();

        A.put("name", this.name);
        A.put("age", this.age);
        A.put("gender", this.gender);
        A.put("imageId", this.imageId);

        return A;
    }
}
