package com.zonesoft.annotations.samples;

import com.zonesoft.annotations.processors.Inspect;

public class LocalExample {

    private int age;

    
    private String name;

    public int getAge() {
        return age;
    }

    @Inspect
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Inspect
    public void setName(String name) {
        this.name = name;
    }

}
