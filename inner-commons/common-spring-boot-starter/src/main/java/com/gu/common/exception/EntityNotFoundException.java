package com.gu.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author FastG
 * @date 2020/7/14 19:15
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " does not exist";
    }
}
