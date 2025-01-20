package de.johannes.commons.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Priority {

    public final byte HIGH = 2, MEDIUM = 1, LOW = 0;

    byte type() default MEDIUM;

}
