package com.alibaba.dubbo.common.extension;

import java.lang.annotation.*;

/**
 * <pre>
 * CreateDate: 2018/8/22 11:21
 * @author sixh chenbin
 * </pre>
 * <p>
 *     对外发部到Proxy处理;
 * </p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ProxyExternal {
    /**
     * 是否对外发部;
     * @return true and false;
     */
    boolean flag() default true;
}
