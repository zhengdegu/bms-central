package com.gu.bms.common.exception;

import org.springframework.util.StringUtils;

/**
 * entity转换异常
 * @author FastG
 * @create 2020-07-13
 **/
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
