package com.zonesoft.annotation.usage.another;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.processors.Inspect;

@PageModel
public class Yellow {

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
