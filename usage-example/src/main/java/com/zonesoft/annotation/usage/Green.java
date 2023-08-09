package com.zonesoft.annotation.usage;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.processors.Inspect;

@PageModel
public class Green {

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
