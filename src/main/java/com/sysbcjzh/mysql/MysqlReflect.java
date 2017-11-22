package com.sysbcjzh.mysql;

import com.sysbcjzh.utils.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class MysqlReflect {
    public static <T> T fromMap(Class<T> c, Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            Class[] paramDef = new Class[0];

            Constructor constructor = c.getConstructor(paramDef);
            obj = constructor.newInstance(new Object[0]);
            if (obj == null)
                return null;
        } catch (Exception e) {
            return null;
        }
        return (T) getEntity(obj, map);
    }

    public static <T> List<T> fromList(Class<T> c, List<Map<String, Object>> mapList) {
        if ((mapList == null) || (mapList.size() == 0)) {
            return null;
        }

        List results = new ArrayList();
        for (Map map : mapList) {
            try {
                Object obj = null;
                Class[] paramDef = new Class[0];

                Constructor constructor = c.getConstructor(paramDef);
                obj = constructor.newInstance(new Object[0]);
                if (obj == null) {
                    return null;
                }
                obj = getEntity(obj, map);
                results.add(obj);
            } catch (Exception localException) {
            }
        }

        return results;
    }

    private static Object getEntity(Object entity, Map<String, Object> keyValue) {
        try {
            Class clazz = entity.getClass();
            Set keys = keyValue.keySet();

            for (Iterator localIterator = keys.iterator(); localIterator.hasNext(); ) {
                Object object = localIterator.next();
                String key = object.toString();
                Object obj = keyValue.get(key);
                if (key.toLowerCase().equals("descript"))
                    key = "desc";
                Field field;
                try {
                    field = clazz.getDeclaredField(key);
                } catch (Exception e) {
                    field = clazz.getSuperclass().getDeclaredField(key);
                }

                field.setAccessible(true);
                if (obj != null) {
                    if ((field.getType().equals(Integer.class)) ||
                            (field.getType().equals(Integer.TYPE))) {
                        field.set(entity, Integer.valueOf(SafeParserInteger(obj
                                .toString())));
                    }
                    if (field.getType().equals(String.class))
                        field.set(entity, obj.toString());
                    else if ((field.getType().equals(Boolean.TYPE)) ||
                            (field.getType().equals(Boolean.class)))
                        field.set(entity, Boolean.valueOf(SafeParserBoolean(obj
                                .toString())));
                    else if ((field.getType().equals(Long.class)) ||
                            (field.getType().equals(Long.TYPE)))
                        field.set(entity, SafeParserLong(obj
                                .toString()));
                    else if ((field.getType().equals(Double.TYPE)) ||
                            (field.getType().equals(Double.class)))
                        field.set(entity, Double.valueOf(SafeParserDouble(obj
                                .toString())));
                    else if (field.getType().equals(List.class))
                        field.set(entity, getStringList(keyValue
                                .get(key).toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    private static Long SafeParserLong(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
        }
        return Long.valueOf(0L);
    }

    private static boolean SafeParserBoolean(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
        }
        return false;
    }

    private static double SafeParserDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
        }
        return 0.0D;
    }

    private static int SafeParserInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public static int executeInsertQuery(JdbcDaoInterface dao, Object entity) {
        if (entity == null) {
            return -1;
        }
        String table = entity.getClass().getName();
        int pos = table.lastIndexOf(".");
        if (pos != -1) {
            table = table.substring(pos + 1);
        }
        String sql = "insert ignore into " + table + " (";
        Field[] declaredFields = ReflectUtils.getDeclaredAndInheritedFields(
                entity.getClass(), false);
        List list = new ArrayList();
        for (Field field : declaredFields)
            try {
                Object value = BeanUtils.getPrivateField(entity, field);
                list.add(value);
                String name = field.getName();
                if (name.toLowerCase().equals("desc")) {
                    name = "descript";
                }
                sql = sql + name + ",";
            } catch (Exception localException1) {
            }
        sql = sql.substring(0, sql.length() - 1) + ") ";
        sql = sql + "values(";
        for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) {
            Object object = localIterator.next();
            sql = sql + "?,";
        }
        sql = sql.substring(0, sql.length() - 1) + ") ";
        try {
            return dao.execute(sql, list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<String> getStringList(String str) {
        List list = new ArrayList();
        String[] strs = str.split("\t");
        for (String string : strs) {
            list.add(string);
        }
        return list;
    }
}