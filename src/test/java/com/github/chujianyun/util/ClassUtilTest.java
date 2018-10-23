package com.github.chujianyun.util;


import com.github.chujianyun.annotation.IgnoreField;
import com.github.chujianyun.domain.Cat;
import com.github.chujianyun.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author 明明如月
 * @date 2018/10/23
 */

public class ClassUtilTest {

    private Person person;

    private Cat cat;
    @Before
    public void init(){
        person = new Person();
        person.setName("张三");
        person.setAge((short)23);
        cat = new Cat("喵咪", (byte) 2);
        person.setCat(cat);
    }

    @Test
    public void covertToNameValueMap() {

        Map<String, String> catNameValueMap = ClassUtil.covertToNameValueMap(cat);
        System.out.println(catNameValueMap);
        Assert.assertEquals(2,catNameValueMap.size() );

        Map<String, String> personNameValueMap = ClassUtil.covertToNameValueMap(person);
        System.out.println(personNameValueMap);
        Assert.assertEquals(4,personNameValueMap.size() );

        Map<String, String> personNameValueMapIgnore = ClassUtil.covertToNameValueMap(person, IgnoreField.class);
        System.out.println(personNameValueMapIgnore);
        Assert.assertEquals(3,personNameValueMapIgnore.size() );

    }

}
