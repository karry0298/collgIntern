package com.collekarry.docside;

public class MyBlaBla
{
    String name;
    String number;

    public MyBlaBla(){}

    public MyBlaBla(String name) {
        this.name = name;
    }

    public MyBlaBla(Long no) {
        this.name = no.toString();
    }

    public String getName() {
        return name;
    }
}
