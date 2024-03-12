package lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributeLock {
    /**
     * the key of lock
     * @return
     */
    String value();

    /**
     * wait time until lock is timeout
     * @return
     */
    long waitTime() default 30;

    /**
     * the lock leasing time
     * @return
     */
    long leaseTime() default 100;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
