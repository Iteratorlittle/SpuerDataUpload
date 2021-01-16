package cn.cqu.demo.util.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {
}
