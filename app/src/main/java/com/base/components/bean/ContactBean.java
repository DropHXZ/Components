package com.base.components.bean;

import java.io.Serializable;

/**
 * 联系人实体
 * Created by admin on 2018/1/9.
 */

public class ContactBean implements Serializable {

    private static final long serialVersionUID = 84684354302523020L;
    public String name;
    public String age;
    public String gender;
    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
