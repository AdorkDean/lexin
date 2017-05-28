package com.caipiao.utils.http;

/**
 * (描述)
 *
 * @author liujie
 */
public class HttpSenderException extends RuntimeException {

    public HttpSenderException() {
    }

    public HttpSenderException(String message) {
        super(message);
    }

    public HttpSenderException(Throwable cause) {
        super(cause);
    }

    public HttpSenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
