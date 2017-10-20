package br.com.greenmile.ponto_api.common.utils;

import java.lang.reflect.Field;

public class EntityUtil {

    private EntityUtil() {
    }

    public static <T> T merge(T oldValue, T newValue) {
        try {
            Class<?> clazz = oldValue.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Object returnValue = clazz.newInstance();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(oldValue);
                Object value2 = field.get(newValue);
                Object value = (value2 != null) ? value2 : value1;
                field.set(returnValue, value);
            }
            return (T) returnValue;
        } catch (Exception e) {
            return null;
        }
    }
}
