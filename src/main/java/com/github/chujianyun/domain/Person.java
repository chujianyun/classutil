package com.github.chujianyun.domain;

import com.github.chujianyun.annotation.IgnoreField;

/**
 * @author 明明如月
 * @date 2018/10/23
 */
public class Person {
    private String name;

    @IgnoreField
    private Short age;

    private Cat cat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
