package com.example.karads.latihanintensandroid.model;

import java.io.Serializable;

/**
 * Created by karads on 1/2/2018.
 */

public class Person implements Serializable{

    private String name;
    private int age;

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
}
