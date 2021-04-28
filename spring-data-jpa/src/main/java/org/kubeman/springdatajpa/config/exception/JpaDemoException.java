package org.kubeman.springdatajpa.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * <pre>
 *     jpademo 实战 module 统一异常
 * </pre>
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
public class JpaDemoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int status;

    private final String error;

    public JpaDemoException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus.value();
        this.error = httpStatus.name();
    }
}
