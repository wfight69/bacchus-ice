package com.davada.application.common;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAuditLog {
    CommandType value() default CommandType.CREATE;

    public static enum CommandType {
        CREATE,
        UPDATE,
        REMOVE;

        private CommandType() {
        }
    }
}
