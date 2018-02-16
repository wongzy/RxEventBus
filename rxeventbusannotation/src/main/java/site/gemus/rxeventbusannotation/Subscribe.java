package site.gemus.rxeventbusannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface Subscribe {
    /**
     * 订阅事件的线程
     * @return 订阅线程
     */
    ThreadMode threadMode() default ThreadMode.MAIN;
}
