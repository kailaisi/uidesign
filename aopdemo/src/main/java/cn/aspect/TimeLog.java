package cn.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/22 10:13
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TimeLog {
}
