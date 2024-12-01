package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Интерфейс AutoInjectable
 * Этот интерфейс является аннотацией и служит для внедрения зависимости в
 * объект любого класса через поле.
 * Поля с повешенной этой аннотацией должны быть инъектированы.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}
