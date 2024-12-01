package org.example.injector;

import org.example.annotation.AutoInjectable;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс Injector
 * Этот класс предназначен для внедрения зависимостей в поля объекта при помощи
 * конфигурационного файла. Класс имеет одно приватное поле properties, которое
 * используется для загрузки настроек из этого файла. Класс содержит один публичный
 * метод, который осуществляет внедрение.
 */
public class Injector {
    private Properties properties = new Properties();

    /**
     * Метод static <T> T inject(T obj)
     * Метод принимает на вход объект любого класса, который задается как параметр.
     * Метод возвращает объект того же класса, но уже с инициализированными полями.
     * Сначала в методе создается массив полей подаваемого на вход объекта и объект
     * класса Properties. Дальше происходит загрузка свойств из файла конфигураций,
     * который содержит информацию о том, какие реализации интерфейсов следует внедрить.
     * Далее происходит перебор полей и поиск тех, которые отмечены аннотацией
     * AutoInjectable. Для полей, помеченных этой аннотацией создаются объекты,
     * классов, которые соответствуют типам этих полей. После поля делаются доступными
     * и в них устанавливаются значения созданных объектов. Метод также может выкидывать
     * исключения, например при неправильных настройках в файле конфигурации.
     * @see AutoInjectable
     * @throws Exception
     */
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
