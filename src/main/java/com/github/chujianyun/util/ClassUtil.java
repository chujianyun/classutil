package com.github.chujianyun.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 明明如月
 * @date 2018/10/23
 */
public class ClassUtil {


    /**
     * 将类中属性（如果属性为对象则递归)，转为map键值对
     *
     * @param obj 带解析的对象
     */
    public static <T> Map<String, String> covertToNameValueMap(T obj) {

        if (obj == null) {
            throw new IllegalArgumentException("the object to convert is empty");
        }

        return covertToNameValueMap(obj, null);
    }

    /**
     * 将类中属性（如果属性为对象则递归)，转为map键值对
     *
     * @param obj               带解析的对象
     * @param excludeAnnotation 排除带该注解的属性
     */
    public static <T> Map<String, String> covertToNameValueMap(T obj, Class<? extends Annotation> excludeAnnotation) {

        if (obj == null) {
            throw new IllegalArgumentException("the object to convert is empty");
        }

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return new HashMap<>(0);
        }

        Map<String, String> nameValueMap = new HashMap<>(declaredFields.length);

        for (Field field : declaredFields) {

            if (excludeAnnotation != null && field.isAnnotationPresent(excludeAnnotation)) {
                continue;
            }
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                continue;
            }

            Class type = field.getType();
            boolean isCommonType = type.isPrimitive()
                    || Number.class.isAssignableFrom(type)
                    || type.equals(Boolean.class)
                    || type.equals(Character.class)
                    || field.getType().equals(String.class);
            if (isCommonType) {
                nameValueMap.put(field.getName(), value.toString());
            } else {
                nameValueMap.putAll(covertToNameValueMap(value, excludeAnnotation));
            }
        }

        return nameValueMap;
    }


}
