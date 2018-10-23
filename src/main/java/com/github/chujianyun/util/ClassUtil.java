package com.github.chujianyun.util;


import com.github.chujianyun.annotation.IgnoreField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 明明如月
 * @date 2018/10/23
 */
public class ClassUtil {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 将类中属性（如果属性为对象则递归)，转为map键值对
     *
     * @param obj 带解析的对象
     */
    public static <T> Map<String, String> covertToNameValueMap(T obj) {
        return covertToNameValueMap(obj, true, true);
    }


    /**
     * 将类中属性（如果属性为对象则递归)，转为map键值对
     *
     * @param obj          带解析的对象
     * @param ignoreAnnotationOn 是否开启注解   true则带有@MapIgnoreField注解的属性将被忽略 {@link IgnoreField}
     */
    public static <T> Map<String, String> covertToNameValueMap(T obj, boolean ignoreAnnotationOn) {
        return covertToNameValueMap(obj, ignoreAnnotationOn, true);
    }

    /**
     * 将类中属性（如果属性为对象则递归)，转为map键值对
     *
     * @param obj                    带解析的对象
     * @param ignoreAnnotationOn           是否开启注解   true则带有@MapIgnoreField注解的属性将被忽略 {@link IgnoreField}
     * @param ignoreSerialVersionUID 是否忽略序列化号
     */
    public static <T> Map<String, String> covertToNameValueMap(T obj, boolean ignoreAnnotationOn, boolean ignoreSerialVersionUID) {

        if (obj == null) {
            throw new IllegalArgumentException("the object to convert is empty");
        }

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return new HashMap<>(0);
        }

        Map<String, String> nameValueMap = new HashMap<>(declaredFields.length);

        for (Field field : declaredFields) {
            // 忽略注解
            if (ignoreAnnotationOn && field.isAnnotationPresent(IgnoreField.class)) {
                continue;
            }
            // 忽略序列化版本号
            if (ignoreSerialVersionUID && field.getName().equals(SERIAL_VERSION_UID)) {
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
                nameValueMap.putAll(covertToNameValueMap(value));
            }
        }

        return nameValueMap;
    }


}
