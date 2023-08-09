package com.zonesoft.annotations.samples;

import com.zonesoft.annotations.processors.BuilderProperty;

public class AnotherPerson {
    private int agezz;

    
    private String namezz;

    public int getAgezz() {
        return agezz;
    }

    @BuilderProperty
    public void setAgezz(int agezz) {
        this.agezz = agezz;
    }

    public String getNamezz() {
        return namezz;
    }

    @BuilderProperty
    public void setNamezz(String namezz) {
        this.namezz = namezz;
    }
}
