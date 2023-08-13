package com.zonesoft.annotation_processing_samples;

import com.zonesoft.annotations.processors.Inspect;

public class AnotherLocalExample {
    private int agezz;

    
    private String namezz;

    public int getAgezz() {
        return agezz;
    }

    @Inspect
    public void setAgezz(int agezz) {
        this.agezz = agezz;
    }

    public String getNamezz() {
        return namezz;
    }

    @Inspect
    public void setNamezz(String namezz) {
        this.namezz = namezz;
    }
}
