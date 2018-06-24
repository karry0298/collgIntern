package com.collekarry.docside;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

public class listOfPeopleClass
{
    String name;
    int age;
    Integer[] imageId;

    public listOfPeopleClass(String name, int age, int imageId) {
        this.name = name;
        this.age = age;
        this.imageId = new Integer[10];
        this.imageId[0] = imageId;
    }

    public listOfPeopleClass(String name, int age) {
        this.name = name;
        this.age = age;
        this.imageId = new Integer[10];
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

    public void setImageId(int imageId, int index) {
        this.imageId[index] = imageId;
    }

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
}
