package org.example.injector;

import org.example.annotation.AutoInjectable;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private Properties properties = new Properties();

    public static <T> T inject(T obj) throws Exception{
        Field[] fields =  obj.getClass().getDeclaredFields();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));
        for(Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String type = field.getAnnotatedType().toString();
                String implementationName = properties.get(type).toString();
                Object value = Class.forName(implementationName).getConstructor().newInstance();
                Field _field = obj.getClass().getDeclaredField(field.getName());
                _field.setAccessible(true);
                _field.set(obj, value);
            }
        }
        return obj;
    }
}
