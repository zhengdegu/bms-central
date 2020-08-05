package com.gu.network.exception;

/**
 * network 同一异常处理类
 *
 * @author FastG
 * @date 2019/8/27 14:28
 */
public class DefaultException extends RuntimeException {


    public DefaultException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DefaultException(String message) {
        super(message);
    }
}
