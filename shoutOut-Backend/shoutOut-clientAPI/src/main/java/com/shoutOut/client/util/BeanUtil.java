package com.shoutOut.client.util;

import com.shoutOut.client.context.BaseContext;
import org.springframework.context.ApplicationContext;

/*TODO::  bean util defining and also add a function
          to lookup the bean and load the beans
* */
public class BeanUtil {
    public static Object loadBean(Class<?> classObj) {
        ApplicationContext context = BaseContext.getAppContext();
        Object beanObj = context.getBean(classObj);
        return beanObj;
    }



}
