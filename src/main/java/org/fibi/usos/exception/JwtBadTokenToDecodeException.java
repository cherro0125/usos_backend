package org.fibi.usos.exception;

public class JwtBadTokenToDecodeException extends Exception {

    public JwtBadTokenToDecodeException() {
        super("JWT: Bad token to decode.");
    }
}
