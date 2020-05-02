package org.fibi.usos.exception.auth;

import org.springframework.security.core.AuthenticationException;

public class BadAuthRequestException extends AuthenticationException {
    public BadAuthRequestException(String msg, Throwable t) {
        super(msg, t);
    }

    public BadAuthRequestException(String msg) {
        super(msg);
    }
}
