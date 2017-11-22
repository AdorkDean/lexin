package com.sysbcjzh.utils;

import com.sysbcjzh.mysql.ReflectUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BeanUtils {
    private static final Logger logger = Logger.getLogger(BeanUtils.class);

    public static Object getAnnotationField(Object obj, Class<? extends Annotation> annotation)
            throws IllegalArgumentException, IllegalAccessException {
        Class pojo = obj.getClass();
        Field[] declaredFields = pojo.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(annotation) != null) {
                Object object = getPrivateField(obj, field);
                return object;
            }
        }
        return null;
    }

    public static <T> T getProperty(Object bean, String name) {
        try {
            return (T) PropertyUtils.getProperty(bean, name);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void setProperty(Object bean, String fieldName, Object value) {
        try {
            PropertyUtils.setProperty(bean, fieldName, value);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static <T> T getPrivateProperty(Object obj, String fieldName)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = ReflectUtils.findField(obj.getClass(), fieldName);
        if (field != null) {
            setAccessible(field);
            return (T) field.get(obj);
        }
        return null;
    }

    public static <T> T getPrivateField(Object obj, Field field)
            throws IllegalArgumentException, IllegalAccessException {
        setAccessible(field);
        return (T) field.get(obj);
    }

    public static void setPrivateProperty(Object obj, String fieldName, Object value)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void setAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers())) ||
                (!Modifier.isPublic(field.getDeclaringClass().getModifiers())))
            field.setAccessible(true);
    }
}